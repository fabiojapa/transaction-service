package com.saka.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @JsonProperty("account_id")
    private Long accountId;

    @NotNull
    @NotEmpty
    @JsonProperty("document_number")
    private String documentNumber;

}
