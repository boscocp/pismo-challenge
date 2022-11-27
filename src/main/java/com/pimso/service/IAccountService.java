package com.pimso.service;

import com.baeldung.dynamodb.models.AccountDTO;

import java.util.List;

public interface IAccountService {
    public AccountDTO create (AccountDTO dto);
    public AccountDTO update(AccountDTO dto);
    public List<AccountDTO> getAll();
    public AccountDTO getById(Long id);
    public void delete(Long id);
}
