package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.port.RiskBureauPort;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Component
public class RiskBureauAdapter implements RiskBureauPort {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauAdapter.class);
    private static final String CIRCUIT_BREAKER_NAME = "riskBureau";
    private static final String RETRY_NAME = "riskBureauRetry";

    private final WebClient webClient;
    private final String bureauBaseUrl;
    private final int timeoutSeconds;

    public RiskBureauAdapter(
            WebClient.Builder webClientBuilder,
            @Value("${external-services.risk-bureau.base-url:http://localhost:8083}") String bureauBaseUrl,
            @Value("${external-services.risk-bureau.timeout-seconds:5}") int timeoutSeconds) {
        this.webClient = webClientBuilder.baseUrl(bureauBaseUrl).build();
        this.bureauBaseUrl = bureauBaseUrl;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackRiskAssessment")
    @Retry(name = RETRY_NAME)
    public Mono<RiskAssessmentResult> assessRisk(UUID customerId, BigDecimal amount) {
        log.info("Consultando buró de riesgos para cliente {} por monto {}", customerId, amount);
        
        Map<String, Object> requestBody = Map.of(
            "customerId", customerId.toString(),
            "requestedAmount", amount.doubleValue(),
            "currency", "USD",
            "timestamp", java.time.Instant.now().toString()
        );

        return webClient.post()
            .uri("/api/v1/risk/assess")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(RiskAssessmentResult.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(result -> log.info("Evaluación de riesgo completada: score={}, riskLevel={}", 
                result.score(), result.riskLevel()))
            .doOnError(error -> log.error("Error en evaluación de riesgo para cliente {}: {}", 
                customerId, error.getMessage()));
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "fallbackCreditLimit")
    @Retry(name = RETRY_NAME)
    public Mono<CreditLimitResult> getCreditLimit(UUID customerId) {
        log.info("Consultando límite de crédito para cliente {}", customerId);

        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/v1/risk/credit-limit")
                .queryParam("customerId", customerId.toString())
                .build())
            .retrieve()
            .bodyToMono(CreditLimitResult.class)
            .timeout(Duration.ofSeconds(timeoutSeconds))
            .doOnSuccess(result -> log.info("Límite de crédito obtenido: approved={}, limit={}", 
                result.approved(), result.limit()))
            .doOnError(error -> log.error("Error consultando límite de crédito para cliente {}: {}", 
                customerId, error.getMessage()));
    }

    private Mono<RiskAssessmentResult> fallbackRiskAssessment(UUID customerId, BigDecimal amount, Throwable throwable) {
        log.warn("Fallback activado para assessRisk. Cliente: {}, causa: {}", customerId, throwable.getMessage());
        return Mono.just(new RiskAssessmentResult(
            0,
            "HIGH",
            "FALLBACK_ACTIVE",
            "No se pudo obtener evaluación de riesgo del buró",
            java.time.Instant.now()
        ));
    }

    private Mono<CreditLimitResult> fallbackCreditLimit(UUID customerId, Throwable throwable) {
        log.warn("Fallback activado para getCreditLimit. Cliente: {}, causa: {}", customerId, throwable.getMessage());
        return Mono.just(new CreditLimitResult(
            customerId,
            false,
            BigDecimal.ZERO,
            "FALLBACK_ACTIVE",
            java.time.Instant.now()
        ));
    }

    public record RiskAssessmentResult(
        int score,
        String riskLevel,
        String status,
        String message,
        java.time.Instant evaluatedAt
    ) {}

    public record CreditLimitResult(
        UUID customerId,
        boolean approved,
        BigDecimal limit,
        String status,
        java.time.Instant evaluatedAt
    ) {}
}