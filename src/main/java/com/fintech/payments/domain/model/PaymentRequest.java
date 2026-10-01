package com.fintech.payments.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentRequest(
    @NotNull(message = "El ID de la transacción es obligatorio")
    UUID transactionId,

    @NotNull(message = "El ID del cliente es obligatorio")
    UUID customerId,

    @NotNull(message = "El ID del comercio es obligatorio")
    UUID merchantId,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser positivo")
    BigDecimal amount,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres")
    String currency,

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres")
    String description,

    @NotNull(message = "La fecha de creación es obligatoria")
    LocalDateTime createdAt,

    @NotBlank(message = "El método de pago es obligatorio")
    String paymentMethod,

    @NotBlank(message = "El token de pago es obligatorio")
    @Size(min = 16, max = 255, message = "El token de pago debe tener entre 16 y 255 caracteres")
    String paymentToken,

    PaymentStatus status
) {
    public PaymentRequest {
        if (transactionId == null) {
            transactionId = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
    }

    public PaymentRequest withStatus(PaymentStatus newStatus) {
        return new PaymentRequest(
            this.transactionId,
            this.customerId,
            this.merchantId,
            this.amount,
            this.currency,
            this.description,
            this.createdAt,
            this.paymentMethod,
            this.paymentToken,
            newStatus
        );
    }
}