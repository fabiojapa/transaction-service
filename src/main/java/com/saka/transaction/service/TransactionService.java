package com.saka.transaction.service;

import static java.time.LocalDateTime.now;

import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.entity.OperationType;
import com.saka.transaction.entity.Transaction;
import com.saka.transaction.repository.AccountRepository;
import com.saka.transaction.repository.OperationTypeRepository;
import com.saka.transaction.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private TransactionRepository transactionRepository;
  private AccountRepository accountRepository;
  OperationTypeRepository operationTypeRepository;

  public TransactionService(
      TransactionRepository transactionRepository,
      AccountRepository accountRepository,
      OperationTypeRepository operationTypeRepository
  ) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
    this.operationTypeRepository = operationTypeRepository;
  }

  /**
   * Creates the record Transaction in the DB
   * @param transactionDto
   * @return transactionDto
   */
  @Transactional
  public TransactionDto create(TransactionDto transactionDto) {
    if (transactionDto == null) {
      throw new IllegalArgumentException("Transaction DTO must not be null");
    }
    var account = findAccountById(transactionDto.getAccountId());
    var operationType = findOperationTypeById(transactionDto.getOperationTypeId());
    var transaction = createTransactionObject(transactionDto, account, operationType);
    transaction = transactionRepository.save(transaction);
    transactionDto.setTransactionId(transaction.getTransactionId());
    transactionDto.setAmount(transaction.getAmount());
    transactionDto.setDescription(operationType.getDescription());
    return transactionDto;
  }

  /**
   * Finds the entity OperationType in the DB
   * @param operationTypeId
   * @return operationType
   */
  private OperationType findOperationTypeById(Integer operationTypeId) {
    return operationTypeRepository.findById(operationTypeId)
        .orElseThrow(
            () -> new EntityNotFoundException(
                "OperationType not found with id: " + operationTypeId
            )
        );
  }

  /**
   * Finds the entity Account in the DB
   * @param accountId
   * @return account
   */
  private Account findAccountById(Long accountId) {
    return accountRepository.findById(accountId)
        .orElseThrow(
            () -> new EntityNotFoundException(
                "Account not found with id: " + accountId
            )
        );
  }

  /**
   * creates the object Transaction
   * @param transactionDto
   * @param account
   * @param operationType
   * @return transaction
   */
  private static Transaction createTransactionObject(TransactionDto transactionDto, Account account,
      OperationType operationType) {
    var transaction = new Transaction();
    transaction.setAccount(account);
    transaction.setOperationType(operationType);
    // setting the amount according to the value:1(credit) / -1(debit)
    transaction.setAmount(
        transactionDto.getAmount().multiply(
            BigDecimal.valueOf(operationType.getValue())
        )
    );
    transaction.setEventDate(now());
    return transaction;
  }
}
