package com.saka.transaction.mapper;

import com.saka.transaction.dto.AccountDto;
import com.saka.transaction.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
  Account toEntity(AccountDto dto);
  AccountDto toDto(Account entity);
}
