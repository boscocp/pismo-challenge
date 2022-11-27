package com.pismo.dynamodb.models;

import lombok.Getter;

@Getter
public class PurchaseDTO {
    private String accountId;
    private String sk = "OPERATION_1";
    private String description = "Compra a vista";
    private String eventDate;

    public PurchaseDTO (String eventDate) {
        this.eventDate = eventDate;
    }
}
