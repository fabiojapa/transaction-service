package com.saka.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @Schema(nullable = true, description = "DO NOT map to json object and allows null")
    @JsonProperty("account_id")
    private Long accountId;

    @NotNull
    @NotEmpty
    @JsonProperty("document_number")
    private String documentNumber;

}
