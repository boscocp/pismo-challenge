package com.pismo.service;

import java.util.List;

import com.pismo.dynamodb.models.OperationDTO;

public interface IOperationService {
    public OperationDTO create (OperationDTO dto);
    public OperationDTO update(OperationDTO dto);
    public List<OperationDTO> getAll(String code);
    public OperationDTO getById(Long id);
    public void delete(Long id);
}
