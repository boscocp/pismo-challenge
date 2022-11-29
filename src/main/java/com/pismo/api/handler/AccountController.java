package com.pismo.api.handler;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.service.IAccountService;
import com.pismo.service.IOperationService;
import com.pismo.dynamodb.models.AccountDTO;
import com.pismo.dynamodb.models.OperationDTO;
import com.pismo.dynamodb.entity.Account;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Configuration
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AccountController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IOperationService operationService;


    @Autowired
    AccountRepository repository;

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
        accountService.create(dto);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping("/account")
    public ResponseEntity<List<AccountDTO>> handle(@RequestBody String code) {
        List<AccountDTO> accounts = accountService.getAll();
        return ResponseEntity.ok().body(accounts);
    }

    @RequestMapping("/account/{id}")
    public ResponseEntity<AccountDTO> handleAccount(@PathVariable String id) {
        AccountDTO accounts = accountService.getById(id);
        return ResponseEntity.ok().body(accounts);
    }

    @PostMapping("/account/operation")
    public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationDTO dto) {
        operationService.create(dto);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping("/account/operation/{id}")
    public ResponseEntity<List<OperationDTO>> handleOperations(@PathVariable String id) {
        List<OperationDTO> accounts = operationService.getAll(id);
        return ResponseEntity.ok().body(accounts);
    }
}
