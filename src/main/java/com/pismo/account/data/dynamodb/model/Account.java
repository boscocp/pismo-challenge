package com.pismo.account.data.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "pismo-account")
public class Account {
    private String account_id;
    private String sk;
    private String ammount;

    public Account() {
    }

    public Account(String ammount, String sk) {
        this.sk = sk;
        this.ammount = ammount;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return account_id;
    }

    @DynamoDBAttribute
    public String getSK() {
        return sk;
    }

    @DynamoDBAttribute
    public String getAmmount() {
        return ammount;
    }

    public void setId(String id) {
        this.account_id = id;
    }

    public void setSK(String sk) {
        this.sk = sk;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }
}