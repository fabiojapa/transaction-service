package com.saka.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/transactions")
public class TransactionController {

  @PostMapping
  public @ResponseBody ResponseEntity<Long> create(@RequestBody Long id) {
    return ResponseEntity.ok(id);
  }

}
