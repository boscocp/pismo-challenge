package com.pismo.dynamodb.enums;

public enum EventType {
    PURCHASE("Compra"),
    PARCELING_PURCHASE("Compra parcelada"),
    WITHDRAW("Saque"),
    PAYMENT("Pagamento");

    private final String value;

    EventType(String operationType) {
        value = operationType;
    }

    public String getValue() {
        return value;
    }
}