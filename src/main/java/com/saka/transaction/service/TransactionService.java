package com.saka.transaction.service;

import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.entity.Transaction;
import com.saka.transaction.mapper.TransactionMapper;
import com.saka.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  
  private TransactionRepository transactionRepository;
  private TransactionMapper transactionMapper;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = TransactionMapper.INSTANCE;
  }

  public TransactionDto create(TransactionDto transactionDto) {
    if (transactionDto == null) {
      throw new IllegalArgumentException("Transaction DTO must not be null");
    }
    Transaction transaction = transactionMapper.toEntity(transactionDto);
    Transaction savedTransaction = transactionRepository.save(transaction);
    return transactionMapper.toDto(savedTransaction);
  }
}
