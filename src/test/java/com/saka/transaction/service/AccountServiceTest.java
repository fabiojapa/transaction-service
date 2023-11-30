package com.saka.transaction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Testing the business rules {@link AccountService}
 */
class AccountServiceTest {

  public static final String DOC_NUMBER = "12345678900";
  public static final long ACCOUNT_ID = 1L;
  private AccountService accountService;

  @Mock
  private AccountRepository accountRepository;
  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    accountService = new AccountService(
       accountRepository,
        objectMapper
    );
  }

  @Test
  void create() {
    var accountDto = new AccountDto(null, DOC_NUMBER);
    var account = new Account(ACCOUNT_ID, DOC_NUMBER);
    Mockito
        .when(accountRepository.save(Mockito.any(Account.class)))
        .thenReturn(account);
    var accountDtoSaved = accountService.create(accountDto);
    assertEquals(ACCOUNT_ID, accountDtoSaved.getAccountId());
    assertEquals(DOC_NUMBER, accountDtoSaved.getDocumentNumber());
    Mockito.verify(accountRepository)
        .save(Mockito.any(Account.class));
  }

  @Test
  void findById() {
    var account = new Account(ACCOUNT_ID, DOC_NUMBER);
    when(accountRepository.findById(ACCOUNT_ID))
        .thenReturn(
            Optional.of(account)
        );
    var accountDtoSaved = accountService.findById(ACCOUNT_ID);
    assertEquals(ACCOUNT_ID, accountDtoSaved.getAccountId());
    assertEquals(DOC_NUMBER, accountDtoSaved.getDocumentNumber());
    Mockito.verify(accountRepository)
        .findById(ACCOUNT_ID);
  }
  @Test
  void findById_NotFound() {
    var account = new Account(ACCOUNT_ID, DOC_NUMBER);
    when(accountRepository.findById(ACCOUNT_ID))
        .thenReturn(
            Optional.ofNullable(null)
        );
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      accountService.findById(ACCOUNT_ID);
    });
    assertTrue(exception.getMessage().contains(Long.toString(ACCOUNT_ID)));
    Mockito.verify(accountRepository)
        .findById(ACCOUNT_ID);
  }

}