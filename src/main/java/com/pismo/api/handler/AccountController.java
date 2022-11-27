package com.pismo.api.handler;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.models.AccountDTO;
import com.pismo.dynamodb.entity.Account;

import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AccountController {
    
    AccountRepository repository;

    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO dto) {
        Account account = new Account(dto.getDocumentNumber(), dto.getSk());
        System.out.println(account);
        this.repository.save(account);
        return ResponseEntity.ok().body("teste");
    }
}
