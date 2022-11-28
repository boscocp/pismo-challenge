package com.pismo.dynamodb.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.pismo.dynamodb.entity.Account;

@EnableScan
public interface AccountRepository extends CrudRepository<Account, String> {
    Optional<Account> findById(String id);
}