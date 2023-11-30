package com.saka.transaction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.entity.OperationType;
import com.saka.transaction.entity.Transaction;
import com.saka.transaction.repository.AccountRepository;
import com.saka.transaction.repository.OperationTypeRepository;
import com.saka.transaction.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Testing the business rules {@link TransactionService}
 */
class TransactionServiceTest {

  public static final long ACCOUNT_ID = 1L;
  public static final int OPERATION_TYPE_ID = 3;
  public static final BigDecimal AMOUNT = BigDecimal.valueOf(123.45);
  public static final String SAQUE = "SAQUE";
  public static final int SAQUE_VALUE = -1;
  public static final long TRANSACTION_ID = 1L;
  private TransactionService transactionService;

  @Mock
  private TransactionRepository transactionRepository;
  @Mock
  private AccountRepository accountRepository;
  @Mock
  private OperationTypeRepository operationTypeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    transactionService = new TransactionService(
        transactionRepository,
        accountRepository,
        operationTypeRepository
    );
  }

  @Test
  void create_OK() {

    var account = new Account(ACCOUNT_ID, "12345678900");
    when(accountRepository.findById(ACCOUNT_ID))
        .thenReturn(
            Optional.of(account)
        );

    OperationType operationType = new OperationType(OPERATION_TYPE_ID, SAQUE, SAQUE_VALUE);
    when(operationTypeRepository.findById(OPERATION_TYPE_ID))
        .thenReturn(
            Optional.of(operationType)
        );
    BigDecimal amount = AMOUNT.multiply(BigDecimal.valueOf(SAQUE_VALUE));
    var transaction = createTransactionObject(account, operationType, amount);
    when(transactionRepository.save(Mockito.any(Transaction.class)))
        .thenReturn(transaction);

    var transactionDto = new TransactionDto(
        null,
        ACCOUNT_ID,
        OPERATION_TYPE_ID,
        SAQUE,
        AMOUNT
    );
    TransactionDto transactionDtoSaved = transactionService.create(transactionDto);
    assertEquals(amount, transactionDtoSaved.getAmount());
    assertNotNull(transactionDtoSaved.getTransactionId());

    verify(accountRepository).findById(ACCOUNT_ID);
    verify(operationTypeRepository).findById(OPERATION_TYPE_ID);
    verify(transactionRepository).save(Mockito.any(Transaction.class));

  }

  private Transaction createTransactionObject(
      Account account,
      OperationType operationType,
      BigDecimal amount
  ) {
    var transaction = new Transaction();
    transaction.setTransactionId(TRANSACTION_ID);
    transaction.setAccount(account);
    transaction.setOperationType(operationType);
    transaction.setAmount(amount);
    transaction.setEventDate(LocalDateTime.now());
    return transaction;
  }

  @Test
  void create_AccountNotFound() {
    when(accountRepository.findById(ACCOUNT_ID))
        .thenReturn(
            Optional.ofNullable(null)
        );
    var transactionDto = new TransactionDto(
        null,
        ACCOUNT_ID,
        OPERATION_TYPE_ID,
        SAQUE,
        AMOUNT
    );
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      transactionService.create(transactionDto);
    });
    assertTrue(exception.getMessage().contains(Long.toString(ACCOUNT_ID)));

    verify(accountRepository).findById(ACCOUNT_ID);
    verify(operationTypeRepository, times(0))
        .findById(OPERATION_TYPE_ID);
    verify(transactionRepository, times(0))
        .save(Mockito.any(Transaction.class));

  }

  @Test
  void create_OperationTypeNotFound() {
    var account = new Account(ACCOUNT_ID, "12345678900");
    when(accountRepository.findById(ACCOUNT_ID))
        .thenReturn(
            Optional.of(account)
        );
    when(operationTypeRepository.findById(OPERATION_TYPE_ID))
        .thenReturn(
            Optional.ofNullable(null)
        );
    var transactionDto = new TransactionDto(
        null,
        ACCOUNT_ID,
        OPERATION_TYPE_ID,
        SAQUE,
        AMOUNT
    );
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      transactionService.create(transactionDto);
    });
    assertTrue(exception.getMessage().contains(Integer.toString(OPERATION_TYPE_ID)));

    verify(accountRepository).findById(ACCOUNT_ID);
    verify(operationTypeRepository).findById(OPERATION_TYPE_ID);
    verify(transactionRepository, times(0))
        .save(Mockito.any(Transaction.class));

  }

}