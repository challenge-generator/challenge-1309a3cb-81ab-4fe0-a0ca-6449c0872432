package com.fintech.payments.domain.port;

import com.fintech.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto para la comunicación con el buró de riesgos.
 * Proporciona acceso a información crediticia y evaluación
 * de riesgo financiero de los clientes.
 */
public interface RiskBureauPort {

    /**
     * Consulta el puntaje crediticio de un cliente.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el puntaje crediticio (mayor es mejor)
     */
    Mono<Integer> getCreditScore(UUID customerId);

    /**
     * Verifica la capacidad financiera del cliente para un monto específico.
     * @param customerId el identificador único del cliente
     * @param amount el monto a evaluar
     * @return Mono que emite true si el cliente tiene capacidad, false si no
     */
    Mono<Boolean> checkFinancialCapacity(UUID customerId, BigDecimal amount);

    /**
     * Obtiene el historial de pagos del cliente.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el número de pagos exitosos en los últimos 12 meses
     */
    Mono<Integer> getPaymentHistoryCount(UUID customerId);

    /**
     * Consulta deuda activa del cliente en el buró.
     * @param customerId el identificador único del cliente
     * @return Mono que emite el monto total de deuda activa
     */
    Mono<BigDecimal> getActiveDebt(UUID customerId);

    /**
     * Evalúa el riesgo general de una transacción.
     * @param paymentRequest la solicitud de pago a evaluar
     * @return Mono que emite un nivel de riesgo (LOW, MEDIUM, HIGH)
     */
    Mono<RiskLevel> evaluateTransactionRisk(PaymentRequest paymentRequest);

    /**
     * Enum que representa los niveles de riesgo evaluados por el buró.
     */
    enum RiskLevel {
        LOW,
        MEDIUM,
        HIGH
    }
}