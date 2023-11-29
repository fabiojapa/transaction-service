package com.saka.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class TransactionServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TransactionServiceApplication.class, args);
  }

}
