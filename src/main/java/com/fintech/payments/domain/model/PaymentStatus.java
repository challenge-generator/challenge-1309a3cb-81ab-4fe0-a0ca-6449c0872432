package com.fintech.payments.domain.model;

public enum PaymentStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED,
    REJECTED,
    REFUNDED,
    CANCELLED;

    public boolean isTerminal() {
        return this == COMPLETED || this == FAILED || this == REJECTED || this == REFUNDED || this == CANCELLED;
    }
}