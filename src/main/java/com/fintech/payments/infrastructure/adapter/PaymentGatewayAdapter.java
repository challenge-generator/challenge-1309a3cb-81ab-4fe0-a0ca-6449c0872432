package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.port.PaymentGatewayPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetryBackoffSpec;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Component
public class PaymentGatewayAdapter implements PaymentGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(PaymentGatewayAdapter.class);
    private static final String CIRCUIT_BREAKER_NAME = "paymentGateway";
    private static final String RETRY_NAME = "paymentGatewayRetry";

    private final WebClient webClient;
    private final String gatewayBaseUrl;
    private final int timeoutSeconds;
    private final boolean simulationMode;

    public PaymentGatewayAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${external-services.payment-gateway.base-url:http://localhost:8084}") String gatewayBaseUrl,
            @Value("${external-services.payment-gateway.timeout-seconds:10}") int timeoutSeconds,
            @Value("${external-services.payment-gateway.simulation-mode:false}") boolean simulationMode) {
        this.webClient = webClientBuilder.baseUrl(gatewayBaseUrl).build();
        this.gatewayBaseUrl = gatewayBaseUrl;
        this.timeoutSeconds = timeoutSeconds;
        this.simulationMode = simulationMode;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackProcessPayment")
    @Retry(name = RETRY_NAME)
    public Mono<PaymentGatewayResponse> processPayment(PaymentGatewayRequest request) {
        log.info("Procesando pago a través del gateway. TransactionId: {}, Amount: {}", 
            request.transactionId(), request.amount());

        if (simulationMode) {
            return simulateGatewayResponse(request);
        }

        Map<String, Object> gatewayRequestBody = Map.of(
            "transactionId", request.transactionId().toString(),
            "customerId", request.customerId().toString(),
            "amount", request.amount().doubleValue(),
            "currency", request.currency(),
            "paymentMethod", request.paymentMethod(),
            "merchantId", request.merchantId(),
            "description", request.description() != null ? request.description() : "",
            "metadata", request.metadata() != null ? request.metadata() : Map.of()
        );

        return webClient.post()
            .uri("/api/v1/payments/process")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(gatewayRequestBody)
            .retrieve()
            .bodyToMono(PaymentGatewayResponse.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(response -> log.info("Pago procesado exitosamente. TransactionId: {}, Status: {}", 
                response.transactionId(), response.status()))
            .doOnError(error -> log.error("Error procesando pago. TransactionId: {}, Error: {}", 
                request.transactionId(), error.getMessage()))
            .onErrorResume(error -> handleGatewayError(request, error));
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackRefundPayment")
    @Retry(name = RETRY_NAME)
    public Mono<PaymentGatewayResponse> refundPayment(UUID transactionId, BigDecimal amount, String reason) {
        log.info("Procesando reembolso. TransactionId: {}, Amount: {}, Reason: {}", 
            transactionId, amount, reason);

        if (simulationMode) {
            return simulateRefundResponse(transactionId, amount);
        }

        Map<String, Object> refundRequestBody = Map.of(
            "originalTransactionId", transactionId.toString(),
            "refundAmount", amount.doubleValue(),
            "reason", reason != null ? reason : "Customer request",
            "timestamp", java.time.Instant.now().toString()
        );

        return webClient.post()
            .uri("/api/v1/payments/refund")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(refundRequestBody)
            .retrieve()
            .bodyToMono(PaymentGatewayResponse.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(response -> log.info("Reembolso procesado. TransactionId: {}, Status: {}", 
                response.transactionId(), response.status()))
            .doOnError(error -> log.error("Error en reembolso. TransactionId: {}, Error: {}", 
                transactionId, error.getMessage()));
    }

    @Override
    public Mono<PaymentGatewayResponse> getPaymentStatus(UUID transactionId) {
        log.info("Consultando estado del pago. TransactionId: {}", transactionId);

        if (simulationMode) {
            return simulateStatusCheck(transactionId);
        }

        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/v1/payments/status")
                .queryParam("transactionId", transactionId.toString())
                .build())
            .retrieve()
            .bodyToMono(PaymentGatewayResponse.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(response -> log.debug("Estado del pago: {}", response.status()))
            .doOnError(error -> log.error("Error consultando estado: {}", error.getMessage()));
    }

    private Mono<PaymentGatewayResponse> handleGatewayError(PaymentGatewayRequest request, Throwable error) {
        if (error instanceof org.springframework.web.reactive.function.client.WebClientResponseException) {
            WebClientResponseException wcre = (WebClientResponseException) error;
            log.error("Error HTTP del gateway: {} - {}", wcre.getStatusCode(), wcre.getResponseBodyAsString());
            return Mono.just(new PaymentGatewayResponse(
                request.transactionId(),
                "FAILED",
                "GATEWAY_ERROR: " + wcre.getStatusCode(),
                wcre.getResponseBodyAsString(),
                java.time.Instant.now()
            ));
        }
        return Mono.error(error);
    }

    private Mono<PaymentGatewayResponse> simulateGatewayResponse(PaymentGatewayRequest request) {
        log.debug("Modo simulación activado para processPayment");
        return Mono.delay(Duration.ofMillis(500))
            .map(tick -> new PaymentGatewayResponse(
                request.transactionId(),
                "APPROVED",
                "SIMULATION_SUCCESS",
                "Pago aprobado en modo simulación",
                java.time.Instant.now()
            ));
    }

    private Mono<PaymentGatewayResponse> simulateRefundResponse(UUID transactionId, BigDecimal amount) {
        log.debug("Modo simulación activado para refundPayment");
        return Mono.delay(Duration.ofMillis(300))
            .map(tick -> new PaymentGatewayResponse(
                transactionId,
                "REFUNDED",
                "SIMULATION_SUCCESS",
                "Reembolso procesado en modo simulación",
                java.time.Instant.now()
            ));
    }

    private Mono<PaymentGatewayResponse> simulateStatusCheck(UUID transactionId) {
        log.debug("Modo simulación activado para getPaymentStatus");
        return Mono.just(new PaymentGatewayResponse(
            transactionId,
            "PENDING",
            "SIMULATION",
            "Estado simulado",
            java.time.Instant.now()
        ));
    }

    private Mono<PaymentGatewayResponse> fallbackProcessPayment(PaymentGatewayRequest request, Throwable throwable) {
        log.warn("Fallback activado para processPayment. TransactionId: {}, causa: {}", 
            request.transactionId(), throwable.getMessage());
        return Mono.just(new PaymentGatewayResponse(
            request.transactionId(),
            "PENDING",
            "CIRCUIT_OPEN",
            "Gateway no disponible, pago en cola para retry",
            java.time.Instant.now()
        ));
    }

    private Mono<PaymentGatewayResponse> fallbackRefundPayment(UUID transactionId, BigDecimal amount, 
                                                                 String reason, Throwable throwable) {
        log.warn("Fallback activado para refundPayment. TransactionId: {}, causa: {}", 
            transactionId, throwable.getMessage());
        return Mono.just(new PaymentGatewayResponse(
            transactionId,
            "PENDING",
            "CIRCUIT_OPEN",
            "Gateway no disponible para reembolso",
            java.time.Instant.now()
        ));
    }
}