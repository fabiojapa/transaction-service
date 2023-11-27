package com.saka.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {

  @Id
  @Column(name = "account_id")
  private Long id;

}
