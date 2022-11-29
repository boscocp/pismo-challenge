package com.pismo.dynamodb.repository;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;

@EnableScan
public interface AccountRepository extends CrudRepository<Account, AccountId> {
    List<Account> findAllById(String code);

    List<Account> findAllByIdAndSkStartsWith(String code, 
        String eventType);
}