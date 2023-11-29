package com.saka.transaction.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;
  private ObjectMapper objectMapper;

  public AccountService(AccountRepository accountRepository, ObjectMapper objectMapper) {
    this.accountRepository = accountRepository;
    this.objectMapper = objectMapper;
  }

  public AccountDto create(AccountDto accountDto) {
    if (accountDto == null) {
      throw new IllegalArgumentException("Account DTO must not be null");
    }
    Account account = objectMapper.convertValue(accountDto, Account.class);
    Account savedAccount = accountRepository.save(account);
    return objectMapper.convertValue(savedAccount, AccountDto.class);
  }

  public AccountDto findById(Long id) {
    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    return objectMapper.convertValue(account, AccountDto.class);
  }

}
