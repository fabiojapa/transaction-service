package com.saka.transaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service to deal with the Entity Account
 */
@Service
public class AccountService {

  private AccountRepository accountRepository;
  private ObjectMapper objectMapper;

  public AccountService(
      AccountRepository accountRepository,
      ObjectMapper objectMapper
  ) {
    this.accountRepository = accountRepository;
    this.objectMapper = objectMapper;
  }

  /**
   * Creates the record Account in the DB
   * @param accountDto
   * @return accountDto
   */
  @Transactional
  public AccountDto create(AccountDto accountDto) {
    if (accountDto == null) {
      throw new IllegalArgumentException("Account DTO must not be null");
    }
    var account = objectMapper.convertValue(accountDto, Account.class);
    account = accountRepository.save(account);
    return objectMapper.convertValue(account, AccountDto.class);
  }

  /**
   * Finds the entity Account in the DB
   * @param id
   * @return accountDto
   */
  public AccountDto findById(Long id) {
    var account = accountRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    return objectMapper.convertValue(account, AccountDto.class);
  }

}
