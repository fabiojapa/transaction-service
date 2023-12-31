package com.saka.transaction.controller;

import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.service.TransactionService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

  private TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public @ResponseBody ResponseEntity<TransactionDto> create(@Valid @RequestBody TransactionDto transactionDto) {
    transactionDto = transactionService.create(transactionDto);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(transactionDto.getTransactionId()).toUri();

    return ResponseEntity.created(location).build();
  }

}
