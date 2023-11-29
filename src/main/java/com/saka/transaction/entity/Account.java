package com.saka.transaction.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Account implements Serializable {

  @JsonProperty("account_id")
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long accountId;

  @JsonProperty("document_number")
  @Column(name = "document_number")
  private String documentNumber;

}
