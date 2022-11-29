package com.pismo.service;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;
import com.pismo.dynamodb.models.AccountDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
// TODO mudar tudo pra estrutura da conta, implementar interface do servico, depois servico


@Service
@Transactional
public class AccountServiceImplementation implements IAccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public AccountDTO create(AccountDTO dto) {
        AccountId accountId = new AccountId("metadata#".concat(dto.getDocumentNumber()));
        Account account = new Account(accountId, dto.getDocumentNumber());
        repository.save(account);
        return dto;
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<AccountDTO> getAll() {
        List <AccountDTO> accountsDTO = new ArrayList<>();
        for (Account element : repository.findAll()) {
            accountToDTO(accountsDTO, element);
        }
        return accountsDTO;
    }

    private void accountToDTO(List <AccountDTO> accountsDTO, Account element) {
        if(element.getDocumentNumber() != null)
            accountsDTO.add(new AccountDTO(element.getDocumentNumber()));
    }

    @Override
    public AccountDTO getById(String id) {
        List<Account> accounts =  repository.findAllByIdAndSkStartsWith(id, "metadata");
        return new AccountDTO(accounts.get(0).getDocumentNumber());
    }

    @Override
    public AccountDTO update(AccountDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

}
