package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface PaymentRepositoryPort {
    Mono<PaymentRequest> save(PaymentRequest paymentRequest);

    Mono<PaymentRequest> findById(UUID transactionId);

    Flux<PaymentRequest> findByCustomerId(UUID customerId);

    Flux<PaymentRequest> findByStatus(PaymentStatus status);

    Mono<PaymentRequest> updateStatus(UUID transactionId, PaymentStatus newStatus);

    Mono<Void> deleteById(UUID transactionId);

    Flux<PaymentRequest> findAll();
}