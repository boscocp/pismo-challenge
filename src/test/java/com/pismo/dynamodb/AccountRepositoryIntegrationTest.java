package com.pismo.dynamodb;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.pismo.Application;
import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;
import com.pismo.dynamodb.models.OperationDTO;
import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.rule.LocalDbCreationRule;
import com.pismo.service.IAccountService;
import com.pismo.service.IOperationService;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { "amazon.dynamodb.endpoint=http://localhost:8000/", "amazon.aws.accesskey=test1", "amazon.aws.secretkey=test231" })
public class AccountRepositoryIntegrationTest {
    private static final int PURCHASE = 1;
    private static final int PARCELING_PURCHASE = 2;
    private static final int WITHDRAW = 3;
    private static final int PAYMENT = 4;
    private static final String METADATA = "metadata";
    private static final String ID = "12j3n1km23n1m23n";

    @ClassRule
    public static LocalDbCreationRule dynamoDB = new LocalDbCreationRule();

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    AccountRepository repository;

    @Autowired
    IAccountService accountService;

    @Autowired
    IOperationService operationService;

    private static final String DOCUMENT_NUMBER = "123123";

    @Before
    public void setup() throws Exception {

        try {
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Account.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            // Do nothing, table already created
        }

        // TODO How to handle different environments. i.e. AVOID deleting all entries in ProductInfo on table
        dynamoDBMapper.batchDelete((List<Account>) repository.findAll());
    }

    @Test
    public void givenItemWithDocumentNumber_whenRunFindAll_thenItemIsFound() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        
        List<Account> result = (List<Account>) repository.findAll();
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result.get(0).getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
    }

    @Test
    public void givenItemWithDocumentNumber_whenRunFindbyId_thenItemIsFound() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        
        List<Account> result2 = (List<Account>) repository.findAllById(ID);
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result2.size(), is(greaterThan(0)));
        assertThat(result2.get(0).getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
    }

    @Test
    public void givenOperationWithDocumentNumber_whenRunFindbyIdandSkStartWith_thenItemIsFound() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        
        List<Account> result3 = (List<Account>) repository.findAllByIdAndSkStartsWith(ID,METADATA);
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result3.size(), is(greaterThan(0)));
        assertThat(result3.get(0).getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
    }

    @Test
    public void givenOperationPurchase_whenRunFindbyIdandSkStartWith_thenAmountNegative() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        Float amount = 240.23f;

        OperationDTO dto = new OperationDTO(PURCHASE,ID, amount);

        operationService.create(dto);
        
        List<OperationDTO> result = (List<OperationDTO>) operationService.getAll(ID);
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result.size(), is(greaterThan(0)));
        assertThat(result.get(0).getAmount(), is(equalTo(-amount)));
    }

    @Test
    public void givenOperationParcelingPurchase_whenRunFindbyIdandSkStartWith_thenAmountNegative() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        Float amount = 240.23f;

        OperationDTO dto = new OperationDTO(PARCELING_PURCHASE,ID, amount);

        operationService.create(dto);
        
        List<OperationDTO> result = (List<OperationDTO>) operationService.getAll(ID);
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result.size(), is(greaterThan(0)));
        assertThat(result.get(0).getAmount(), is(equalTo(-amount)));
    }

    @Test
    public void givenOperationWitdraw_whenRunFindbyIdandSkStartWith_thenAmountNegative() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        Float amount = 240.23f;

        OperationDTO dto = new OperationDTO(WITHDRAW,ID, amount);

        operationService.create(dto);
        
        List<OperationDTO> result = (List<OperationDTO>) operationService.getAll(ID);
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result.size(), is(greaterThan(0)));
        assertThat(result.get(0).getAmount(), is(equalTo(-amount)));
    }

    @Test
    public void givenOperationPayment_whenRunFindbyIdandSkStartWith_thenAmountPositive() {
        AccountId accountId = new AccountId(ID,METADATA);
        Account account = new Account(accountId, DOCUMENT_NUMBER);
        Account newAccount = repository.save(account);
        Float amount = 240.23f;

        OperationDTO dto = new OperationDTO(PAYMENT,ID, amount);

        operationService.create(dto);
        
        List<OperationDTO> result = (List<OperationDTO>) operationService.getAll(ID);
        assertThat(newAccount.getDocumentNumber(), is(equalTo(DOCUMENT_NUMBER)));
        assertThat(result.size(), is(greaterThan(0)));
        assertThat(result.get(0).getAmount(), is(equalTo(amount)));
    }

}
