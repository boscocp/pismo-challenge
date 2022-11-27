package com.baeldung.dynamodb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDTO {
    private String accountId;
    private String sk;
    private String documentNumber;
}
