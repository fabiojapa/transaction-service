package com.saka.transaction.repository;

import com.saka.transaction.entity.OperationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends CrudRepository<OperationType, Integer> {

}
