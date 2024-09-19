package com.fiap.restaurant_management.domain.enums;

public enum Status {
    CONFIRMED(1),
    PENDING(2),
    DENIED(3),
    FINISHED(4);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
