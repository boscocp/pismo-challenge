package com.pismo.dynamodb.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDTO {
    private String sk;
    private String documentNumber;
}
