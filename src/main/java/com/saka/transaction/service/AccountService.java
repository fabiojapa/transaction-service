package com.saka.transaction.service;

import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.entity.Account;
import com.saka.transaction.mapper.AccountMapper;
import com.saka.transaction.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  private AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
    this.accountMapper = AccountMapper.INSTANCE;
  }

  public AccountDto create(AccountDto accountDto) {
    if (accountDto == null) {
      throw new IllegalArgumentException("Account DTO must not be null");
    }
    Account account = accountMapper.toEntity(accountDto);
    Account savedAccount = accountRepository.save(account);
    return accountMapper.toDto(savedAccount);
  }

  public AccountDto findById(Long id) {
    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    return accountMapper.toDto(account);
  }

}
