package com.saka.transaction.service;

import static org.junit.jupiter.api.Assertions.*;

import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.entity.OperationType;
import com.saka.transaction.entity.Transaction;
import com.saka.transaction.repository.AccountRepository;
import com.saka.transaction.repository.OperationTypeRepository;
import com.saka.transaction.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TransactionServiceTest {

  public static final long ACCOUNT_ID = 1L;
  public static final int OPERATION_TYPE_ID = 3;
  public static final BigDecimal AMOUNT = BigDecimal.valueOf(123.45);
  public static final String SAQUE = "SAQUE";
  public static final int SAQUE_VALUE = -1;
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
  void create() {

    Account account = new Account(ACCOUNT_ID, "12345678900");
    Mockito.when(accountRepository.findById(ACCOUNT_ID))
        .thenReturn(
            Optional.of(account)
        );

    OperationType operationType = new OperationType(OPERATION_TYPE_ID, SAQUE, SAQUE_VALUE);
    Mockito.when(operationTypeRepository.findById(OPERATION_TYPE_ID))
        .thenReturn(
            Optional.of(operationType)
        );
    var transaction = new Transaction();
    transaction.setTransactionId(1L);
    transaction.setAccount(account);
    transaction.setOperationType(operationType);
    BigDecimal amount = AMOUNT.multiply(BigDecimal.valueOf(SAQUE_VALUE));
    transaction.setAmount(amount);
    transaction.setEventDate(LocalDateTime.now());
    Mockito.when(transactionRepository.save(Mockito.any(Transaction.class)))
        .thenReturn(transaction);

    TransactionDto transactionDto = TransactionDto
        .builder()
        .accountId(ACCOUNT_ID)
        .operationTypeId(OPERATION_TYPE_ID)
        .amount(AMOUNT)
        .build();
    TransactionDto transactionDtoSaved = transactionService.create(transactionDto);
    assertEquals(amount, transactionDtoSaved.getAmount());
    assertNotNull(transactionDtoSaved.getTransactionId());

    Mockito.verify(accountRepository).findById(ACCOUNT_ID);
    Mockito.verify(operationTypeRepository).findById(OPERATION_TYPE_ID);
    Mockito.verify(transactionRepository).save(Mockito.any(Transaction.class));

  }
}