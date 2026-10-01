package com.fintech.payments.application.usecase;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import com.fintech.payments.domain.port.PaymentRepositoryPort;
import com.fintech.payments.domain.port.PaymentGatewayPort;
import com.fintech.payments.domain.port.AntifraudServicePort;
import com.fintech.payments.domain.port.RiskBureauPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProcessPaymentUseCase Tests")
class ProcessPaymentUseCaseTest {

    @Mock
    private PaymentRepositoryPort paymentRepositoryPort;

    @Mock
    private PaymentGatewayPort paymentGatewayPort;

    @Mock
    private AntifraudServicePort antifraudServicePort;

    @Mock
    private RiskBureauPort riskBureauPort;

    private ProcessPaymentUseCase processPaymentUseCase;

    @BeforeEach
    void setUp() {
        processPaymentUseCase = new ProcessPaymentUseCase(
                paymentRepositoryPort,
                paymentGatewayPort,
                antifraudServicePort,
                riskBureauPort
        );
    }

    @Test
    @DisplayName("Should process payment successfully when all validations pass")
    void shouldProcessPaymentSuccessfully() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        PaymentRequest savedRequest = request.withStatus(PaymentStatus.PROCESSING);
        PaymentRequest approvedRequest = savedRequest.withStatus(PaymentStatus.APPROVED);

        when(paymentRepositoryPort.save(any(PaymentRequest.class))).thenReturn(Mono.just(savedRequest));
        when(antifraudServicePort.validate(any(PaymentRequest.class))).thenReturn(Mono.just(true));
        when(riskBureauPort.assess(any(PaymentRequest.class))).thenReturn(Mono.just(true));
        when(paymentGatewayPort.process(any(PaymentRequest.class))).thenReturn(Mono.just(approvedRequest));
        when(paymentRepositoryPort.updateStatus(any(), any())).thenReturn(Mono.just(approvedRequest));

        StepVerifier.create(processPaymentUseCase.execute(request))
                .expectNextMatches(payment -> payment.status() == PaymentStatus.APPROVED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should reject payment when antifraud validation fails")
    void shouldRejectPaymentWhenAntifraudFails() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        PaymentRequest savedRequest = request.withStatus(PaymentStatus.PROCESSING);
        PaymentRequest rejectedRequest = savedRequest.withStatus(PaymentStatus.REJECTED);

        when(paymentRepositoryPort.save(any(PaymentRequest.class))).thenReturn(Mono.just(savedRequest));
        when(antifraudServicePort.validate(any(PaymentRequest.class))).thenReturn(Mono.just(false));
        when(paymentRepositoryPort.updateStatus(any(), any())).thenReturn(Mono.just(rejectedRequest));

        StepVerifier.create(processPaymentUseCase.execute(request))
                .expectNextMatches(payment -> payment.status() == PaymentStatus.REJECTED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should reject payment when risk bureau assessment fails")
    void shouldRejectPaymentWhenRiskBureauFails() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.PENDING,
                LocalDateTime.now(),
                null
        );

        PaymentRequest savedRequest = request.withStatus(PaymentStatus.PROCESSING);
        PaymentRequest rejectedRequest = savedRequest.withStatus(PaymentStatus.REJECTED);

        when(paymentRepositoryPort.save(any(PaymentRequest.class))).thenReturn(Mono.just(savedRequest));
        when(antifraudServicePort.validate(any(PaymentRequest.class))).thenReturn(Mono.just(true));
        when(riskBureauPort.assess(any(PaymentRequest.class))).thenReturn(Mono.just(false));
        when(paymentRepositoryPort.updateStatus(any(), any())).thenReturn(Mono.just(rejectedRequest));

        StepVerifier.create(processPaymentUseCase.execute(request))
                .expectNextMatches(payment -> payment.status() == PaymentStatus.REJECTED)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return payment when found by transaction ID")
    void shouldReturnPaymentWhenFoundById() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();
        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findById(transactionId)).thenReturn(Mono.just(request));

        StepVerifier.create(processPaymentUseCase.findById(transactionId))
                .expectNext(request)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return empty when payment not found by transaction ID")
    void shouldReturnEmptyWhenPaymentNotFound() {
        UUID transactionId = UUID.randomUUID();

        when(paymentRepositoryPort.findById(transactionId)).thenReturn(Mono.empty());

        StepVerifier.create(processPaymentUseCase.findById(transactionId))
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return payments when found by customer ID")
    void shouldReturnPaymentsWhenFoundByCustomerId() {
        UUID customerId = UUID.randomUUID();
        UUID transactionId1 = UUID.randomUUID();
        UUID transactionId2 = UUID.randomUUID();

        PaymentRequest request1 = new PaymentRequest(
                transactionId1,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        PaymentRequest request2 = new PaymentRequest(
                transactionId2,
                customerId,
                new BigDecimal("200.00"),
                "USD",
                PaymentStatus.PROCESSING,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findByCustomerId(customerId))
                .thenReturn(Flux.just(request1, request2));

        StepVerifier.create(processPaymentUseCase.findByCustomerId(customerId))
                .expectNext(request1)
                .expectNext(request2)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return payments when found by status")
    void shouldReturnPaymentsWhenFoundByStatus() {
        UUID transactionId = UUID.randomUUID();
        UUID customerId = UUID.randomUUID();

        PaymentRequest request = new PaymentRequest(
                transactionId,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findByStatus(PaymentStatus.APPROVED))
                .thenReturn(Flux.just(request));

        StepVerifier.create(processPaymentUseCase.findByStatus(PaymentStatus.APPROVED))
                .expectNext(request)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should return all payments")
    void shouldReturnAllPayments() {
        UUID customerId = UUID.randomUUID();
        UUID transactionId1 = UUID.randomUUID();
        UUID transactionId2 = UUID.randomUUID();

        PaymentRequest request1 = new PaymentRequest(
                transactionId1,
                customerId,
                new BigDecimal("100.00"),
                "USD",
                PaymentStatus.APPROVED,
                LocalDateTime.now(),
                null
        );

        PaymentRequest request2 = new PaymentRequest(
                transactionId2,
                customerId,
                new BigDecimal("200.00"),
                "USD",
                PaymentStatus.REJECTED,
                LocalDateTime.now(),
                null
        );

        when(paymentRepositoryPort.findAll())
                .thenReturn(Flux.just(request1, request2));

        StepVerifier.create(processPaymentUseCase.findAll())
                .expectNext(request1)
                .expectNext(request2)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should delete payment successfully")
    void shouldDeletePaymentSuccessfully() {
        UUID transactionId = UUID.randomUUID();

        when(paymentRepositoryPort.deleteById(transactionId)).thenReturn(Mono.empty());

        StepVerifier.create(processPaymentUseCase.deleteById(transactionId))
                .verifyComplete();
    }
}