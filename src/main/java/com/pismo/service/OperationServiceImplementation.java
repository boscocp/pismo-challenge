package com.pismo.service;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;
import com.pismo.dynamodb.models.OperationDTO;
import com.pismo.dynamodb.enums.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
// TODO mudar tudo pra estrutura da conta, implementar interface do servico, depois servico
import java.util.Map;

@Service
@Transactional
public class OperationServiceImplementation implements IOperationService {
    public static final Map<Integer, String> operations = new HashMap<>();
    static {
        operations.put(1, "Compra");
        operations.put(2, "Compra parcelada");
        operations.put(3, "Saque");
        operations.put(4, "Pagamento");
    }

    @Autowired
    AccountRepository repository;

    @Override
    public OperationDTO create(OperationDTO dto) {
        LocalDateTime lt = LocalDateTime.now();

        AccountId accountId = new AccountId(dto.getAccountId(), convertToSk(dto, lt.toString()));
        Account account = new Account(accountId,
                lt.toString(),
                computeAmount(dto, dto.getOperationTypeId()),
                operations.get(dto.getOperationTypeId()),
                dto.getOperationTypeId().toString());
        repository.save(account);
        return dto;
    }

    private String computeAmount(OperationDTO dto, int operation) {
        return operation == 4 ? dto.getAmount().toString() : "-".concat(dto.getAmount().toString());
    }

    private String convertToSk(OperationDTO dto, String now) {
        return "operation_".concat(dto.getOperationTypeId().toString() + "#" + now);
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
