package com.fintech.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Hooks;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import java.time.Duration;

@SpringBootApplication
public class PaymentsApplication {
    public static void main(String[] args) {
        // Configuración global para debugging reactivo
        Hooks.onOperatorDebug();
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                .build();
    }

    @Bean
    public CircuitBreakerConfig customCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(5)
                .recordExceptions(
                        org.springframework.web.reactive.function.client.WebClientResponseException.class,
                        java.util.concurrent.TimeoutException.class,
                        java.io.IOException.class
                )
                .build();
    }

    @Bean
    public CircuitBreaker circuitBreaker(CircuitBreakerConfig circuitBreakerConfig) {
        return CircuitBreaker.of("paymentServiceCircuitBreaker", circuitBreakerConfig);
    }

    @Bean
    public CircuitBreakerOperator<Object> circuitBreakerOperator(CircuitBreaker circuitBreaker) {
        return CircuitBreakerOperator.of(circuitBreaker);
    }
}