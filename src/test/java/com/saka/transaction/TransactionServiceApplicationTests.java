package com.saka.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Testing the application context and the flyway scripts
 */
@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceApplicationTests {

  @Test
  void contextLoads() {
  }

}
