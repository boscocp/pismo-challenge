package com.pismo.dynamodb.models;

public class WithdrawDTO {
    private String accountId;
    private String sk = "OPERATION_3";
    private String description = "Saque";
    private String eventDate;

    public WithdrawDTO (String eventDate) {
        this.eventDate = eventDate;
    }
}
