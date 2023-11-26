package com.saka.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<Long> findById(@PathVariable Long id) {
    return ResponseEntity.ok(id);
  }

}
