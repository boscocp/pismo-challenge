package com.pismo.dynamodb.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccountDTO {
    @JsonProperty("sk")
    private String sk;
    @JsonProperty("documentNumber")
    private String documentNumber;
}
