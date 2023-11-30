package com.saka.transaction.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saka.transaction.TransactionServiceApplication;
import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.dto.TransactionDto;
import java.math.BigDecimal;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TransactionServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Order(2)
class TransactionControllerIT {

  public static final String DOCUMENT_NUMBER = "12345678900";
  public static final long ACCOUNT_ID = 1L;
  public static final int OPERATION_TYPE_ID = 3;
  public static final BigDecimal AMOUNT = BigDecimal.valueOf(123.45);
  public static final String SAQUE = "SAQUE";

  @LocalServerPort
  private int port;
  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @BeforeEach
  void setUp() {
  }

  @Test
  void create() {
    createAccount();
    ResponseEntity<String> response = createTransaction();

    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    String actual = response.getHeaders()
        .get(HttpHeaders.LOCATION)
        .get(0);

    assertTrue(actual.contains("/transactions/1"));
  }

  private ResponseEntity<String> createAccount() {
    AccountDto accountDto = new AccountDto(null, DOCUMENT_NUMBER);

    HttpEntity<AccountDto> entity = new HttpEntity<AccountDto>(accountDto, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/accounts"),
        HttpMethod.POST, entity, String.class);
    return response;
  }

  private ResponseEntity<String> createTransaction() {
    var transactionDto = new TransactionDto(
        null,
        ACCOUNT_ID,
        OPERATION_TYPE_ID,
        SAQUE,
        AMOUNT
    );

    var entity = new HttpEntity<TransactionDto>(transactionDto, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/transactions"),
        HttpMethod.POST, entity, String.class);
    return response;
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}