package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto para la comunicación con el gateway de pagos externo.
 * Abstrae las operaciones de procesamiento real del dinero a través
 * de providers de pago externos (tarjetas, transferencias, etc.).
 */
public interface PaymentGatewayPort {

    /**
     * Procesa un pago a través del gateway.
     * @param paymentRequest la solicitud de pago con los datos de la transacción
     * @return Mono que emite el identificador de autorización del gateway
     */
    Mono<String> processPayment(PaymentRequest paymentRequest);

    /**
     * Cancela una transacción previamente autorizada.
     * @param transactionId el identificador de la transacción a cancelar
     * @return Mono que completa sin error si la cancelación fue exitosa
     */
    Mono<Void> cancelPayment(UUID transactionId);

    /**
     * Consulta el estado de una transacción en el gateway.
     * @param transactionId el identificador de la transacción
     * @return Mono que emite el estado actual de la transacción
     */
    Mono<PaymentGatewayStatus> getPaymentStatus(UUID transactionId);

    /**
     * Reembolsa una transacción completada.
     * @param transactionId el identificador de la transacción a reembolsar
     * @param amount el monto a reembolsar (puede ser parcial)
     * @return Mono que emite el identificador del reembolso
     */
    Mono<String> refundPayment(UUID transactionId, BigDecimal amount);

    /**
     * Verifica la disponibilidad del gateway.
     * @return Mono que emite true si el gateway está disponible, false en caso contrario
     */
    Mono<Boolean> isGatewayAvailable();

    /**
     * Enum que representa los estados posibles de una transacción en el gateway.
     */
    enum PaymentGatewayStatus {
        PENDING,
        AUTHORIZED,
        CAPTURED,
        DECLINED,
        CANCELLED,
        REFUNDED,
        FAILED
    }
}