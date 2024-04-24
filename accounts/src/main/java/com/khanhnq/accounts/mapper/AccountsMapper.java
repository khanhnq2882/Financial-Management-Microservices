package com.khanhnq.accounts.mapper;

import com.khanhnq.accounts.dto.AccountsDto;
import com.khanhnq.accounts.entity.Accounts;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountsMapper {
    AccountsMapper ACCOUNTS_MAPPER = Mappers.getMapper(AccountsMapper.class);
    AccountsDto mapToAccountsDto (Accounts accounts);
    void updateAccounts (@MappingTarget Accounts accounts, AccountsDto accountsDto);
}
