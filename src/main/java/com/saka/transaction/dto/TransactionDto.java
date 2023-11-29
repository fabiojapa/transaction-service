package com.saka.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TransactionDto {

  @JsonProperty("transaction_id")
  private Long transactionId;

  @NotNull
  @JsonProperty("account_id")
  private Long accountId;

  @NotNull
  @JsonProperty("operation_type_id")
  private Integer operationTypeId;

  @NotNull
  private BigDecimal amount;

}
