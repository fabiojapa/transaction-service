package com.saka.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDto {

  @JsonProperty("account_id")
  private Long accountId;

  @JsonProperty("operation_type_id")
  private Integer operationTypeId;

  private BigDecimal amount;

}
