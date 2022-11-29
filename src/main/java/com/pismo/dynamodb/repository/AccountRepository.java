package com.pismo.dynamodb.repository;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;

@EnableScan
@Repository
public interface AccountRepository extends CrudRepository<Account, AccountId> {
    List<Account> findAllById(String id);

    List<Account> findAllByIdAndSkStartsWith(String id, 
        String sk);
}