package com.fintech.payments.infrastructure.adapter;

import com.fintech.payments.domain.model.PaymentRequest;
import com.fintech.payments.domain.model.PaymentStatus;
import com.fintech.payments.domain.port.PaymentRepositoryPort;
import io.r2dbc.postgresql.codec.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(PaymentRepositoryAdapter.class);
    private static final String TABLE_NAME = "payments";

    private final DatabaseClient databaseClient;

    public PaymentRepositoryAdapter(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
        initializeTable();
    }

    private void initializeTable() {
        databaseClient.sql("""
            CREATE TABLE IF NOT EXISTS """ + TABLE_NAME + """ (
                transaction_id UUID PRIMARY KEY,
                amount DECIMAL(19,4) NOT NULL,
                currency VARCHAR(3) NOT NULL,
                source_account VARCHAR(50) NOT NULL,
                destination_account VARCHAR(50) NOT NULL,
                customer_id UUID NOT NULL,
                payment_type VARCHAR(20) NOT NULL,
                status VARCHAR(30) NOT NULL,
                metadata JSONB,
                created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
                updated_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
            )
            """)
            .fetch()
            .rowsUpdated()
            .subscribe(
                rows -> log.debug("Tabla {} verificada/creada", TABLE_NAME),
                error -> log.error("Error al inicializar tabla: {}", error.getMessage())
            );
    }

    @Override
    public Mono<PaymentRequest> save(PaymentRequest paymentRequest) {
        log.debug("Guardando pago: {}", paymentRequest.transactionId());
        
        String metadataJson = paymentRequest.metadata() != null 
            ? new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(paymentRequest.metadata())
            : null;
        
        return databaseClient.sql("""
            INSERT INTO """ + TABLE_NAME + """
            (transaction_id, amount, currency, source_account, destination_account, 
             customer_id, payment_type, status, metadata, created_at, updated_at)
            VALUES (:transactionId, :amount, :currency, :sourceAccount, :destinationAccount,
                    :customerId, :paymentType, :status, :metadata, :createdAt, :updatedAt)
            ON CONFLICT (transaction_id) DO UPDATE SET
                status = EXCLUDED.status,
                metadata = EXCLUDED.metadata,
                updated_at = EXCLUDED.updated_at
            RETURNING *
            """)
            .bind("transactionId", paymentRequest.transactionId())
            .bind("amount", paymentRequest.amount().doubleValue())
            .bind("currency", paymentRequest.currency())
            .bind("sourceAccount", paymentRequest.sourceAccount())
            .bind("destinationAccount", paymentRequest.destinationAccount())
            .bind("customerId", paymentRequest.customerId())
            .bind("paymentType", paymentRequest.paymentType())
            .bind("status", paymentRequest.status().name())
            .bind("metadata", metadataJson != null ? Json.of(metadataJson) : null)
            .bind("createdAt", Instant.now())
            .bind("updatedAt", Instant.now())
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .first()
            .doOnSuccess(saved -> log.info("Pago guardado exitosamente: {}", saved.transactionId()))
            .doOnError(error -> log.error("Error al guardar pago: {}", error.getMessage()));
    }

    @Override
    public Mono<PaymentRequest> findById(UUID transactionId) {
        log.debug("Buscando pago por ID: {}", transactionId);
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " WHERE transaction_id = :transactionId")
            .bind("transactionId", transactionId)
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .first()
            .doOnSuccess(result -> log.debug("Pago encontrado: {}", transactionId))
            .doOnError(error -> log.error("Error al buscar pago por ID: {}", error.getMessage()));
    }

    @Override
    public Flux<PaymentRequest> findByCustomerId(UUID customerId) {
        log.debug("Buscando pagos para cliente: {}", customerId);
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " WHERE customer_id = :customerId ORDER BY created_at DESC")
            .bind("customerId", customerId)
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .all()
            .doOnComplete(() -> log.debug("Búsqueda de pagos por cliente completada: {}", customerId));
    }

    @Override
    public Flux<PaymentRequest> findByStatus(PaymentStatus status) {
        log.debug("Buscando pagos con estado: {}", status);
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " WHERE status = :status ORDER BY created_at DESC")
            .bind("status", status.name())
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .all()
            .doOnComplete(() -> log.debug("Búsqueda de pagos por estado completada: {}", status));
    }

    @Override
    public Mono<PaymentRequest> updateStatus(UUID transactionId, PaymentStatus newStatus) {
        log.debug("Actualizando estado de {} a {}", transactionId, newStatus);
        
        return databaseClient.sql("""
            UPDATE """ + TABLE_NAME + """
            SET status = :newStatus, updated_at = :updatedAt
            WHERE transaction_id = :transactionId
            RETURNING *
            """)
            .bind("transactionId", transactionId)
            .bind("newStatus", newStatus.name())
            .bind("updatedAt", Instant.now())
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .first()
            .doOnSuccess(updated -> log.info("Estado actualizado a {} para {}", newStatus, transactionId))
            .doOnError(error -> log.error("Error al actualizar estado: {}", error.getMessage()));
    }

    @Override
    public Mono<Void> deleteById(UUID transactionId) {
        log.debug("Eliminando pago: {}", transactionId);
        
        return databaseClient.sql("DELETE FROM " + TABLE_NAME + " WHERE transaction_id = :transactionId")
            .bind("transactionId", transactionId)
            .fetch()
            .rowsUpdated()
            .flatMap(rows -> rows > 0 ? Mono.empty() : Mono.error(
                new IllegalArgumentException("Pago no encontrado para eliminación: " + transactionId)))
            .then()
            .doOnSuccess(v -> log.info("Pago eliminado: {}", transactionId))
            .doOnError(error -> log.error("Error al eliminar pago: {}", error.getMessage()));
    }

    @Override
    public Flux<PaymentRequest> findAll() {
        log.debug("Consultando todos los pagos");
        
        return databaseClient.sql("SELECT * FROM " + TABLE_NAME + " ORDER BY created_at DESC")
            .map((row, metadata) -> mapRowToPaymentRequest(row))
            .all()
            .doOnComplete(() -> log.debug("Consulta de todos los pagos completada"));
    }

    private PaymentRequest mapRowToPaymentRequest(org.springframework.r2dbc.core.Row row) {
        UUID transactionId = row.get("transaction_id", UUID.class);
        BigDecimal amount = BigDecimal.valueOf(row.get("amount", Double.class));
        String currency = row.get("currency", String.class);
        String sourceAccount = row.get("source_account", String.class);
        String destinationAccount = row.get("destination_account", String.class);
        UUID customerId = row.get("customer_id", UUID.class);
        String paymentType = row.get("payment_type", String.class);
        String statusStr = row.get("status", String.class);
        PaymentStatus status = PaymentStatus.valueOf(statusStr);
        
        Map<String, Object> metadata = null;
        Json metadataJson = row.get("metadata", Json.class);
        if (metadataJson != null) {
            try {
                metadata = new com.fasterxml.jackson.databind.ObjectMapper().readValue(
                    metadataJson.asString(), Map.class);
            } catch (Exception e) {
                log.warn("Error al deserializar metadata para {}: {}", transactionId, e.getMessage());
            }
        }
        
        return new PaymentRequest(
            transactionId,
            amount,
            currency,
            sourceAccount,
            destinationAccount,
            customerId,
            paymentType,
            status,
            metadata
        );
    }
}