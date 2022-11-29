package com.pismo.dynamodb.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pismo.dynamodb.enums.EventType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationDTO {
    @JsonProperty("operationTypeId")
    private Integer operationTypeId;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("amount")
    private Float amount;
}