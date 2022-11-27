package com.pismo.dynamodb.models;

public class PaymentDTO {
    private String accountId;
    private String sk = "OPERATION_4";
    private String description = "Pagamento";
    private String eventDate;

    public PaymentDTO (String eventDate) {
        this.eventDate = eventDate;
    }
}
