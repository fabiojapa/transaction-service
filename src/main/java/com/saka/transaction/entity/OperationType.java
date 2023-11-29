package com.saka.transaction.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OperationType implements Serializable {

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
