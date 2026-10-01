package com.fintech.payments.infrastructure.controller;

import com.fintech.payments.application.usecase.ProcessPaymentUseCase;
import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    private final ProcessPaymentUseCase processPaymentUseCase;

    public PaymentController(ProcessPaymentUseCase processPaymentUseCase) {
        this.processPaymentUseCase = processPaymentUseCase;
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentRequest>> processPayment(@RequestBody PaymentRequest request) {
        log.info("Received payment request for transaction: {}", request.transactionId());
        return processPaymentUseCase.execute(request)
                .map(payment -> ResponseEntity.status(HttpStatus.CREATED).body(payment))
                .onErrorResume(error -> {
                    log.error("Error processing payment: {}", error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/{transactionId}")
    public Mono<ResponseEntity<PaymentRequest>> getPayment(@PathVariable UUID transactionId) {
        log.info("Fetching payment for transaction: {}", transactionId);
        return processPaymentUseCase.findById(transactionId)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public Flux<PaymentRequest> getPaymentsByCustomer(@PathVariable UUID customerId) {
        log.info("Fetching payments for customer: {}", customerId);
        return processPaymentUseCase.findByCustomerId(customerId);
    }

    @GetMapping("/status/{status}")
    public Flux<PaymentRequest> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        log.info("Fetching payments with status: {}", status);
        return processPaymentUseCase.findByStatus(status);
    }

    @GetMapping
    public Flux<PaymentRequest> getAllPayments() {
        log.info("Fetching all payments");
        return processPaymentUseCase.findAll();
    }

    @DeleteMapping("/{transactionId}")
    public Mono<ResponseEntity<Void>> deletePayment(@PathVariable UUID transactionId) {
        log.info("Deleting payment for transaction: {}", transactionId);
        return processPaymentUseCase.deleteById(transactionId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(error -> {
                    log.error("Error deleting payment: {}", error.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}