package com.saka.transaction.mapper;

import com.saka.transaction.dto.TransactionDto;
import com.saka.transaction.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
  Transaction toEntity(TransactionDto dto);
  TransactionDto toDto(Transaction entity);
}
