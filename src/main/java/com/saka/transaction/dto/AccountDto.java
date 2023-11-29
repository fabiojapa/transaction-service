package com.saka.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountDto(
    Long id,
    @JsonProperty("document_number")
    String documentNumber
) {

}
