package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Puerto para la comunicación con el motor antifraude.
 * Define el contrato que la capa de dominio utiliza para validar
 * transacciones contra el servicio de detección de fraude.
 */
public interface AntifraudServicePort {

    /**
     * Valida una solicitud de pago contra el motor antifraude.
     * @param paymentRequest la solicitud de pago a validar
     * @return Mono que emite true si la transacción pasa la validación antifraude,
     *         o false si es rechazada por riesgo de fraude
     */
    Mono<Boolean> validateTransaction(PaymentRequest paymentRequest);

    /**
     * Consulta el historial de fraude de un cliente específico.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el número de intentos de fraude registrados
     */
    Mono<Integer> getFraudAttemptsCount(UUID customerId);

    /**
     * Reporta un intento de fraude asociado a una transacción.
     * @param transactionId el identificador de la transacción sospechosa
     * @param customerId el identificador del cliente involucrado
     * @param reason la razón por la que se reporta el fraude
     * @return Mono que completa sin error si el reporte fue exitoso
     */
    Mono<Void> reportFraudAttempt(UUID transactionId, UUID customerId, String reason);

    /**
     * Obtiene el nivel de riesgo分数 de un cliente.
     * @param customerId el identificador único del cliente
     * @return Mono que emite un valor entre 0 y 100 representando el nivel de riesgo
     */
    Mono<Integer> getRiskScore(UUID customerId);

    /**
     * Verifica si un cliente está en la lista de bloqueo por fraude.
     * @param customerId el identificador único del cliente
     * @return Mono que emite true si el cliente está bloqueado, false en caso contrario
     */
    Mono<Boolean> isCustomerBlocked(UUID customerId);
}