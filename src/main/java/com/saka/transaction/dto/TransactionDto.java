package com.saka.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

  @JsonProperty("transaction_id")
  private Long transactionId;

  @NotNull
  @JsonProperty("account_id")
  private Long accountId;

  @NotNull
  @JsonProperty("operation_type_id")
  private Integer operationTypeId;

  private String description;

  @NotNull
  private BigDecimal amount;

}
