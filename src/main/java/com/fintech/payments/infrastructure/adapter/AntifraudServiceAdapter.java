package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.port.AntifraudServicePort;
import com.fintech.payments.domain.model.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.Duration;
import java.util.Map;
import java.util.HashMap;

@Component
public class AntifraudServiceAdapter implements AntifraudServicePort {

    private static final Logger log = LoggerFactory.getLogger(AntifraudServiceAdapter.class);
    private static final String SERVICE_NAME = "antifraud-service";
    private static final String ENDPOINT_PATH = "/api/v1/antifraud/evaluate";
    private static final Duration TIMEOUT = Duration.ofMillis(500);
    private static final int MAX_RETRY_ATTEMPTS = 3;

    private final WebClient webClient;

    public AntifraudServiceAdapter(@Qualifier("antifraudWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = SERVICE_NAME, fallbackMethod = "evaluateRiskFallback")
    @Retry(name = SERVICE_NAME)
    public Mono<AntifraudResult> evaluateRisk(PaymentRequest paymentRequest) {
        log.debug("Evaluando riesgo antifraude para transacción: {}", paymentRequest.transactionId());
        
        Map<String, Object> requestBody = buildRequestBody(paymentRequest);
        
        return webClient.post()
            .uri(ENDPOINT_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(AntifraudResult.class)
            .timeout(TIMEOUT)
            .doOnSuccess(result -> log.info("Evaluación antifraude completada para {}: approved={}", 
                paymentRequest.transactionId(), result.isApproved()))
            .doOnError(error -> log.error("Error en evaluación antifraude para {}: {}", 
                paymentRequest.transactionId(), error.getMessage()));
    }

    private Mono<AntifraudResult> evaluateRiskFallback(PaymentRequest paymentRequest, Throwable error) {
        log.warn("Fallback de antifraude activado para {}: {}", paymentRequest.transactionId(), error.getMessage());
        
        if (error instanceof java.util.concurrent.TimeoutException) {
            return Mono.just(new AntifraudResult(true, "TIMEOUT_FALLBACK", "Timeout en servicio antifraude -approved por defecto"));
        }
        if (error instanceof org.springframework.web.reactive.function.client.WebClientResponseException) {
            org.springframework.web.reactive.function.client.WebClientResponseException wcre = 
                (org.springframework.web.reactive.function.client.WebClientResponseException) error;
            if (wcre.getStatusCode().is5xxServerError()) {
                return Mono.just(new AntifraudResult(true, "SERVER_ERROR_FALLBACK", "Error 5xx en antifraude - aprobado por defecto"));
            }
        }
        return Mono.just(new AntifraudResult(true, "UNKNOWN_ERROR_FALLBACK", "Error desconocido en antifraude - aprobado por defecto"));
    }

    private Map<String, Object> buildRequestBody(PaymentRequest paymentRequest) {
        Map<String, Object> body = new HashMap<>();
        body.put("transactionId", paymentRequest.transactionId().toString());
        body.put("amount", paymentRequest.amount());
        body.put("currency", paymentRequest.currency());
        body.put("sourceAccount", paymentRequest.sourceAccount());
        body.put("destinationAccount", paymentRequest.destinationAccount());
        body.put("customerId", paymentRequest.customerId().toString());
        body.put("paymentType", paymentRequest.paymentType());
        
        if (paymentRequest.metadata() != null) {
            body.put("metadata", paymentRequest.metadata());
        }
        return body;
    }

    public static class AntifraudResult {
        private final boolean approved;
        private final String rejectionReason;
        private final String riskScore;

        public AntifraudResult(boolean approved, String rejectionReason, String riskScore) {
            this.approved = approved;
            this.rejectionReason = rejectionReason;
            this.riskScore = riskScore;
        }

        public boolean isApproved() {
            return approved;
        }

        public String getRejectionReason() {
            return rejectionReason;
        }

        public String getRiskScore() {
            return riskScore;
        }
    }
}