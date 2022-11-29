package com.pismo.api.handler;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.service.IAccountService;
import com.pismo.dynamodb.models.AccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;

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
    IAccountService service;

    @PostMapping("/account") //(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
        service.create(dto);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping("/account")
    public ResponseEntity<List<AccountDTO>> handle() {
        return ResponseEntity.ok().body(service.getAll());
    }
}
