package com.saka.transaction.controller;

import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.service.AccountService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

  private AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping
  public @ResponseBody ResponseEntity<AccountDto> create(@Valid @RequestBody AccountDto accountDto) {
    accountDto = accountService.create(accountDto);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(accountDto.getAccountId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<AccountDto> findById(@PathVariable Long id) {
    AccountDto accountDto = accountService.findById(id);
    return ResponseEntity.ok(accountDto);
  }

}
