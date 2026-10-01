package com.fintech.payments.application.usecase;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import com.fintech.payments.domain.port.PaymentRepositoryPort;
import com.fintech.payments.domain.port.AntifraudServicePort;
import com.fintech.payments.domain.port.RiskBureauPort;
import com.fintech.payments.domain.port.PaymentGatewayPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;
import java.util.UUID;

@Service
public class ProcessPaymentUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessPaymentUseCase.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_DELAY_MS = 100L;

    private final PaymentRepositoryPort paymentRepository;
    private final AntifraudServicePort antifraudService;
    private final RiskBureauPort riskBureau;
    private final PaymentGatewayPort paymentGateway;

    public ProcessPaymentUseCase(
            PaymentRepositoryPort paymentRepository,
            AntifraudServicePort antifraudService,
            RiskBureauPort riskBureau,
            PaymentGatewayPort paymentGateway) {
        this.paymentRepository = paymentRepository;
        this.antifraudService = antifraudService;
        this.riskBureau = riskBureau;
        this.paymentGateway = paymentGateway;
    }

    public Mono<PaymentRequest> execute(PaymentRequest paymentRequest) {
        log.info("Iniciando procesamiento de pago para transactionId: {}", paymentRequest.transactionId());
        
        return validateAndEnrichPayment(paymentRequest)
            .flatMap(this::performAntifraudCheck)
            .flatMap(this::performRiskBureauCheck)
            .flatMap(this::executePaymentGateway)
            .flatMap(this::persistPaymentWithStatus)
            .doOnSuccess(result -> log.info("Pago procesado exitosamente: {}", result.transactionId()))
            .doOnError(error -> log.error("Error en procesamiento de pago: {}", error.getMessage()));
    }

    private Mono<PaymentRequest> validateAndEnrichPayment(PaymentRequest paymentRequest) {
        return Mono.just(paymentRequest)
            .filter(req -> req.transactionId() != null)
            .switchIfEmpty(Mono.defer(() -> {
                PaymentRequest enriched = new PaymentRequest(
                    UUID.randomUUID(),
                    paymentRequest.amount(),
                    paymentRequest.currency(),
                    paymentRequest.sourceAccount(),
                    paymentRequest.destinationAccount(),
                    paymentRequest.customerId(),
                    paymentRequest.paymentType(),
                    PaymentStatus.PENDING,
                    paymentRequest.metadata()
                );
                return Mono.just(enriched);
            }))
            .filter(req -> req.amount() != null && req.amount().compareTo(java.math.BigDecimal.ZERO) > 0)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Monto inválido para la transacción")));
    }

    private Mono<PaymentRequest> performAntifraudCheck(PaymentRequest paymentRequest) {
        log.debug("Ejecutando verificación antifraude para: {}", paymentRequest.transactionId());
        
        return antifraudService.evaluateRisk(paymentRequest)
            .flatMap(antifraudResult -> {
                if (antifraudResult.isApproved()) {
                    log.info("Verificación antifraude aprobada para: {}", paymentRequest.transactionId());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.ANTIFRAUD_APPROVED));
                } else {
                    log.warn("Verificación antifraude rechazada para: {}. Razón: {}", 
                        paymentRequest.transactionId(), antifraudResult.rejectionReason());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.ANTIFRAUD_REJECTED));
                }
            })
            .onErrorResume(error -> {
                log.error("Error en servicio antifraude: {}", error.getMessage());
                return Mono.just(paymentRequest.withStatus(PaymentStatus.ANTIFRAUD_FAILED));
            });
    }

    private Mono<PaymentRequest> performRiskBureauCheck(PaymentRequest paymentRequest) {
        log.debug("Consultando buró de riesgos para: {}", paymentRequest.transactionId());
        
        if (paymentRequest.status() == PaymentStatus.ANTIFRAUD_REJECTED) {
            return Mono.just(paymentRequest);
        }
        
        return riskBureau.queryRiskScore(paymentRequest.customerId())
            .flatMap(riskScore -> {
                if (riskScore.getScore() <= riskScore.getThreshold()) {
                    log.info("Buró de riesgos aprobado para cliente: {}", paymentRequest.customerId());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.RISK_BUREAU_APPROVED));
                } else {
                    log.warn("Buró de riesgos rechazado para cliente: {}. Score: {}", 
                        paymentRequest.customerId(), riskScore.getScore());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.RISK_BUREAU_REJECTED));
                }
            })
            .onErrorResume(error -> {
                log.error("Error en buró de riesgos: {}", error.getMessage());
                return Mono.just(paymentRequest.withStatus(PaymentStatus.RISK_BUREAU_FAILED));
            });
    }

    private Mono<PaymentRequest> executePaymentGateway(PaymentRequest paymentRequest) {
        log.debug("Ejecutando gateway de pagos para: {}", paymentRequest.transactionId());
        
        PaymentStatus currentStatus = paymentRequest.status();
        if (currentStatus == PaymentStatus.ANTIFRAUD_REJECTED || 
            currentStatus == PaymentStatus.RISK_BUREAU_REJECTED) {
            return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_SKIPPED));
        }
        
        return paymentGateway.processPayment(paymentRequest)
            .flatMap(gatewayResponse -> {
                if (gatewayResponse.isSuccessful()) {
                    log.info("Gateway de pagos aprobó transacción: {}", paymentRequest.transactionId());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_APPROVED));
                } else {
                    log.warn("Gateway de pagos rechazado para: {}. Código: {}", 
                        paymentRequest.transactionId(), gatewayResponse.getErrorCode());
                    return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_REJECTED));
                }
            })
            .onErrorResume(error -> {
                log.error("Error en gateway de pagos: {}", error.getMessage());
                return Mono.just(paymentRequest.withStatus(PaymentStatus.GATEWAY_FAILED));
            });
    }

    private Mono<PaymentRequest> persistPaymentWithStatus(PaymentRequest paymentRequest) {
        log.debug("Persistiendo estado final del pago: {} con estado: {}", 
            paymentRequest.transactionId(), paymentRequest.status());
        
        return paymentRepository.save(paymentRequest)
            .flatMap(saved -> {
                if (saved.status().isTerminal()) {
                    log.info("Transacción {} completada con estado terminal: {}", 
                        saved.transactionId(), saved.status());
                    return Mono.just(saved);
                } else {
                    log.warn("Transacción {} finalizada con estado no terminal: {}", 
                        saved.transactionId(), saved.status());
                    return Mono.just(saved);
                }
            });
    }

    public Mono<PaymentRequest> getPaymentById(UUID transactionId) {
        log.info("Consultando pago por ID: {}", transactionId);
        return paymentRepository.findById(transactionId)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Pago no encontrado: " + transactionId)));
    }

    public Flux<PaymentRequest> getPaymentsByCustomer(UUID customerId) {
        log.info("Consultando pagos para cliente: {}", customerId);
        return paymentRepository.findByCustomerId(customerId);
    }

    public Flux<PaymentRequest> getPaymentsByStatus(PaymentStatus status) {
        log.info("Consultando pagos con estado: {}", status);
        return paymentRepository.findByStatus(status);
    }

    public Mono<PaymentRequest> updatePaymentStatus(UUID transactionId, PaymentStatus newStatus) {
        log.info("Actualizando estado de pago {} a {}", transactionId, newStatus);
        return paymentRepository.updateStatus(transactionId, newStatus)
            .switchIfEmpty(Mono.error(new IllegalArgumentException("Pago no encontrado para actualizar: " + transactionId)));
    }

    public Mono<Void> cancelPayment(UUID transactionId) {
        log.info("Cancelando pago: {}", transactionId);
        return paymentRepository.findById(transactionId)
            .flatMap(payment -> {
                if (payment.status().isTerminal()) {
                    return Mono.error(new IllegalStateException("No se puede cancelar un pago en estado terminal: " + payment.status()));
                }
                return paymentRepository.updateStatus(transactionId, PaymentStatus.CANCELLED);
            })
            .then();
    }

    public Flux<PaymentRequest> getAllPayments() {
        log.info("Consultando todos los pagos");
        return paymentRepository.findAll();
    }
}