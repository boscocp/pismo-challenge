package com.pismo.dynamodb.entity;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.NoArgsConstructor;

@DynamoDBTable(tableName = "pismo-account")
@NoArgsConstructor
public class Account {

    @Id
    private AccountId accountId;
    private String documentNumber;
    private String amount;
    private String eventDate;
    private String description;
    private String operationType;

    public Account(AccountId accountId, String documentNumber) {
        this.accountId = accountId;
        this.documentNumber = documentNumber;
    }

    public Account(AccountId accountId, String eventDate, String description, String amount, String operationType) {
        this.accountId = accountId;
        this.eventDate = eventDate;
        this.description = description;
        this.amount = amount;
        this.operationType = operationType;
    }

    @DynamoDBAttribute
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @DynamoDBAttribute
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @DynamoDBAttribute
    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @DynamoDBAttribute
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return this.accountId != null
                ? this.accountId.getId()
                : null;
    }

    public void setId(String id) {
        if (this.accountId == null) {
            this.accountId = new AccountId();
        }

        this.accountId.setId(id);
    }

    @DynamoDBRangeKey(attributeName = "sk")
    public String getSk() {
        return this.accountId != null
                ? this.accountId.getSk()
                : null;
    }

    @DynamoDBAttribute
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setSk(String sk) {
        if (this.accountId == null) {
            this.accountId = new AccountId();
        }

        this.accountId.setSk(sk);
    }

}
