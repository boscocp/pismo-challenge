package com.pismo.api.handler;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.models.AccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.dynamodb.entity.Account;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Configuration
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AccountController {

    @Autowired
    AccountRepository repository;

    @PostMapping("/account") //(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
        Account account = new Account("123123", "teste");
        this.repository.save(account);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping("/account")
    public ResponseEntity<List<Account>> handle() {
        List<Account> result = (List<Account>) repository.findAll();
        return ResponseEntity.ok().body(result);
    }
}
