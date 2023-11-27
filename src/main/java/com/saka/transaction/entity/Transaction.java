package com.saka.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaction {

  @Id
  @Column(name = "account_id")
  private Long id;

}
