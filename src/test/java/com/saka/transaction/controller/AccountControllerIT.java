package com.saka.transaction.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saka.transaction.TransactionServiceApplication;
import com.saka.transaction.dto.AccountDto;
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
@Order(1)
class AccountControllerIT {

  public static final String DOCUMENT_NUMBER = "12345678900";

  @LocalServerPort
  private int port;
  TestRestTemplate restTemplate = new TestRestTemplate();
  HttpHeaders headers = new HttpHeaders();

  @BeforeEach
  void setUp() {
  }

  @Test
  @Order(1)
  void create() {
    ResponseEntity<String> response = createAccount();

    assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
    String actual = response.getHeaders()
        .get(HttpHeaders.LOCATION)
        .get(0);

    assertTrue(actual.contains("/accounts/1"));
  }

  private ResponseEntity<String> createAccount() {
    AccountDto accountDto = new AccountDto(null, DOCUMENT_NUMBER);

    HttpEntity<AccountDto> entity = new HttpEntity<AccountDto>(accountDto, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/accounts"),
        HttpMethod.POST, entity, String.class);
    return response;
  }

  @Test
  void findById_Ok() throws JSONException {
    createAccount();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/accounts/1"),
        HttpMethod.GET, entity, String.class);
    String expected = """
        {
            "account_id": 1,
            "document_number": "12345678900"
        }""";
    JSONAssert.assertEquals(expected, response.getBody(), false);
    assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
  }

  @Test
  void findById_NotFound() throws JSONException {
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        createURLWithPort("/accounts/5"),
        HttpMethod.GET, entity, String.class);

    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode().value());
  }

  private String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }
}