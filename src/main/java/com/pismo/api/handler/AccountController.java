package com.pismo.api.handler;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.models.AccountDTO;
import com.pismo.dynamodb.entity.Account;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AccountController {

    @Autowired
    AccountRepository repository;

    @PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO dto) {
        Account account = new Account(dto.getDocumentNumber(), dto.getSk());
        this.repository.save(account);
        return ResponseEntity.ok().body("Hello client");
    }

    @RequestMapping("/account")
    public ResponseEntity<List<Account>> handle() {
        List<Account> result = (List<Account>) repository.findAll();
        return ResponseEntity.ok().body(result);
    }
}
