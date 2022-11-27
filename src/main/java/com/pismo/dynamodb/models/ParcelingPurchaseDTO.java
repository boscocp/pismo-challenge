package com.pismo.dynamodb.models;

public class ParcelingPurchaseDTO {
    private String accountId;
    private String sk = "OPERATION_2";
    private String description = "Compra parcelada";
    private String eventDate;

    public ParcelingPurchaseDTO (String eventDate) {
        this.eventDate = eventDate;
    }
}