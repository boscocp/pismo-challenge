package com.pismo.service;

import com.pismo.dynamodb.models.AccountDTO;

import java.util.List;

public interface IAccountService {
    public AccountDTO create (AccountDTO dto);
    public AccountDTO update(AccountDTO dto);
    public List<AccountDTO> getAll();
    public AccountDTO getById(String id);
    public void delete(String id);
}
