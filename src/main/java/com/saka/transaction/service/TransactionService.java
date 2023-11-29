package com.saka.transaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.entity.Transaction;
import com.saka.transaction.repository.AccountRepository;
import com.saka.transaction.repository.OperationTypeRepository;
import com.saka.transaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  
  private TransactionRepository transactionRepository;
  private AccountRepository accountRepository;
  OperationTypeRepository operationTypeRepository;
  private ObjectMapper objectMapper;

  public TransactionService(
      TransactionRepository transactionRepository,
      AccountRepository accountRepository,
      OperationTypeRepository operationTypeRepository,
      ObjectMapper objectMapper
  ) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
    this.operationTypeRepository = operationTypeRepository;
    this.objectMapper = objectMapper;
  }

  @Transactional
  public TransactionDto create(TransactionDto transactionDto) {
    if (transactionDto == null) {
      throw new IllegalArgumentException("Transaction DTO must not be null");
    }
    Transaction transaction = objectMapper.convertValue(transactionDto, Transaction.class);
    // TODO SKMT handle the logic for amount dealing with the operation typeixi amor

    Transaction savedTransaction = transactionRepository.save(transaction);
    return objectMapper.convertValue(savedTransaction, TransactionDto.class);
  }
}
