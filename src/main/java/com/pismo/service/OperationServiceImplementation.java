package com.pismo.service;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;
import com.pismo.dynamodb.models.OperationDTO;
import com.pismo.dynamodb.enums.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
// TODO mudar tudo pra estrutura da conta, implementar interface do servico, depois servico
import java.util.Map;

@Service
@Transactional
public class OperationServiceImplementation implements IOperationService {
    @Autowired
    AccountRepository repository;

    @Override
    public OperationDTO create(OperationDTO dto) {
        Map<Integer,String> op = new HashMap<Integer,String>();
        op.put(1, "Compra");
        op.put( 2, "Compra parcelada");
        op.put( 3, "Saque");
        op.put( 4, "Pagamento");

        AccountId accountId = new AccountId(convertToSk(dto));
        Account account = new Account(accountId,
                "now-10-10-2022",
                dto.getAmount().toString(),
                op.get(dto.getOperationTypeId()),
                dto.getOperationTypeId().toString());
        repository.save(account);
        return dto;
    }

    private String convertToSk(OperationDTO dto) {
        return "operation#".concat(dto.getOperationTypeId().toString());
    }

    @Override
    public OperationDTO update(OperationDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OperationDTO> getAll(String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OperationDTO getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

}
