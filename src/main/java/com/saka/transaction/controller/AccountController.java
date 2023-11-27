package com.saka.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/accounts")
public class AccountController {

  @PostMapping
  public @ResponseBody ResponseEntity<Long> create(@RequestBody Long id) {
    return ResponseEntity.ok(id);
  }

  @GetMapping("/{id}")
  public @ResponseBody ResponseEntity<Long> findById(@PathVariable Long id) {
    return ResponseEntity.ok(id);
  }

}
