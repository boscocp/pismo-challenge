package com.pismo.dynamodb.models;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDTO {
    @JsonProperty("operationTypeId")
    private Integer operationTypeId;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("amount")
    private Float amount;
}