#!/bin/bash

# -- > Create DynamoDb Table
echo Creating  DynamoDb \'messages\' table ...
echo $(awslocal dynamodb create-table --cli-input-json '{"TableName":"pismo-account", "KeySchema":[{"AttributeName":"account_id","KeyType":"HASH"}, {"AttributeName":"sk","KeyType":"RANGE"}], "AttributeDefinitions":[{"AttributeName":"account_id","AttributeType":"S"}, {"AttributeName":"sk","AttributeType":"S"}],"BillingMode":"PAY_PER_REQUEST"}')

# --> List DynamoDb Tables
echo Listing tables ...
echo $(awslocal dynamodb list-tables)