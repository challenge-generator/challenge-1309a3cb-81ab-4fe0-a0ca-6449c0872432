package com.fintech.payments.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.function.Function;

@Configuration
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Value("${webclient.connect-timeout-ms:5000}")
    private int connectTimeoutMs;

    @Value("${webclient.response-timeout-ms:15000}")
    private int responseTimeoutMs;

    @Value("${webclient.pool.max-connections:100}")
    private int maxConnections;

    @Value("${webclient.pool.pending-queue-size:200}")
    private int pendingQueueSize;

    @Value("${webclient.buffer-size-bytes:262144}")
    private int bufferSizeBytes;

    @Bean
    public ConnectionProvider connectionProvider() {
        log.info("Configurando ConnectionProvider con maxConnections={}, pendingQueueSize={}", 
            maxConnections, pendingQueueSize);

        return ConnectionProvider.builder("payment-service-pool")
            .maxConnections(maxConnections)
            .pendingAcquireMaxCount(pendingQueueSize)
            .pendingAcquireTimeout(Duration.ofMillis(responseTimeoutMs))
            .maxIdleTime(Duration.ofMillis(connectTimeoutMs))
            .maxLifeTime(Duration.ofMinutes(5))
            .evictInBackground(Duration.ofSeconds(30))
            .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        log.info("Configurando HttpClient con connectTimeout={}ms, responseTimeout={}ms", 
            connectTimeoutMs, responseTimeoutMs);

        return HttpClient.create(connectionProvider)
            .responseTimeout(Duration.ofMillis(responseTimeoutMs))
            .compress(true)
            .keepAlive(true)
            .doOnConnected(conn -> conn
                .addHandlerLast(new reactor.netty.handler.logging.LoggingHandler("reactor.netty"))
            );
    }

    @Bean
    public WebClient.Builder webClientBuilder(HttpClient httpClient) {
        log.info("Configurando WebClient con bufferSize={} bytes", bufferSizeBytes);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer
                .defaultCodecs()
                .maxInMemorySize(bufferSizeBytes))
            .build();

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .exchangeStrategies(exchangeStrategies)
            .defaultHeader("Accept", "application/json")
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("X-Service-Origin", "payment-service")
            .filter((request, next) -> {
                log.debug("WebClient request: {} {}", request.method(), request.url());
                return next.exchange(request)
                    .doOnSuccess(response -> 
                        log.debug("WebClient response: {} {}", response.statusCode(), request.url()))
                    .doOnError(error -> 
                        log.error("WebClient error: {} - {}", request.url(), error.getMessage()));
            })
            .filter((request, next) -> 
                next.exchange(request)
                    .onErrorResume(org.springframework.web.reactive.function.client.WebClientResponseException.class,
                        error -> {
                            log.error("Error en respuesta HTTP: {} {}", error.getStatusCode(), error.getMessage());
                            return reactor.core.publisher.Mono.error(error);
                        })
            );
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        log.info("Configurando CircuitBreakerRegistry con configuración por defecto");

        CircuitBreakerConfig defaultConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(50)
            .slowCallRateThreshold(80)
            .waitDurationInOpenState(Duration.ofSeconds(30))
            .slowCallDurationThreshold(Duration.ofSeconds(5))
            .permittedNumberOfCallsInHalfOpenState(10)
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(10)
            .minimumNumberOfCalls(5)
            .automaticTransitionFromOpenToHalfOpenEnabled(true)
            .build();

        CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(defaultConfig);

        registry.getEventPublisher()
            .onEvent(event -> log.info("CircuitBreaker event: {} - {}", 
                event.getCircuitBreakerName(), event.getEventType()));

        return registry;
    }

    @Bean
    public RetryRegistry retryRegistry() {
        log.info("Configurando RetryRegistry con configuración por defecto");

        RetryConfig defaultConfig = RetryConfig.custom()
            .maxAttempts(3)
            .waitDuration(Duration.ofMillis(500))
            .retryExceptions(
                org.springframework.web.reactive.function.client.WebClientRequestException.class,
                org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable.class
            )
            .ignoreExceptions(
                org.springframework.web.reactive.function.client.WebClientResponseException.BadRequest.class,
                org.springframework.web.reactive.function.client.WebClientResponseException.Unauthorized.class,
                org.springframework.web.reactive.function.client.WebClientResponseException.Forbidden.class
            )
            .build();

        RetryRegistry registry = RetryRegistry.of(defaultConfig);

        registry.getEventPublisher()
            .onEvent(event -> log.info("Retry event: {} - attempt {}", 
                event.getRetryName(), event.getAttemptNumber()));

        return registry;
    }
}