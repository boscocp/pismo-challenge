package com.pismo.dynamodb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDTO {
    private String accountId;
    private String sk;
    private String documentNumber;

    public AccountDTO(String documentNumber, String sk) {
        this.documentNumber = documentNumber;
        this.sk = sk;
    }
}
