package com.saka.transaction.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
public class OperationType {

  @JsonProperty("operation_type_id")
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer operationTypeId;

  private String description;

  /**
   * 1: credit
   * -1: debit
   */
  private Integer value;

}
