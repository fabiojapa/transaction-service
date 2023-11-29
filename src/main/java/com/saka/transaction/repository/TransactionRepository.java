package com.saka.transaction.repository;

import com.saka.transaction.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
