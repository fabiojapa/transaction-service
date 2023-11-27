package com.saka.transaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OperationType {

  @Id
  @Column(name = "operation_typeid")
  private Long id;

}
