package com.pismo.service;

import com.pismo.dynamodb.repository.AccountRepository;
import com.pismo.dynamodb.entity.Account;
import com.pismo.dynamodb.entity.AccountId;
import com.pismo.dynamodb.models.OperationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                operations.get(dto.getOperationTypeId()),
                computeAmount(dto, dto.getOperationTypeId()),
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
    public List<OperationDTO> getAll(String id) {
        List<OperationDTO> operationsDTO = new ArrayList<>();
        for (Account element : repository.findAllByIdAndSkStartsWith(
                id,
                "operation")) {
            operationToDTO(operationsDTO, element);
        }
        return operationsDTO;
    }

    private void operationToDTO(List<OperationDTO> operationsDTO, Account element) {
        operationsDTO.add(new OperationDTO(
            Integer.parseInt(element.getOperationType()), 
            element.getId(), 
            Float.parseFloat(element.getAmount())));
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
