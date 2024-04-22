package com.khanhnq.accounts.mapper;

import com.khanhnq.accounts.dto.AccountsDto;
import com.khanhnq.accounts.entity.Accounts;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-22T22:19:09+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Amazon.com Inc.)"
)
public class AccountsMapperImpl implements AccountsMapper {

    @Override
    public AccountsDto mapToAccountsDto(Accounts accounts) {
        if ( accounts == null ) {
            return null;
        }

        AccountsDto.AccountsDtoBuilder accountsDto = AccountsDto.builder();

        accountsDto.accountNumber( accounts.getAccountNumber() );
        accountsDto.accountType( accounts.getAccountType() );
        accountsDto.branchAddress( accounts.getBranchAddress() );

        return accountsDto.build();
    }

    @Override
    public void updateAccounts(Accounts accounts, AccountsDto accountsDto) {
        if ( accountsDto == null ) {
            return;
        }

        accounts.setAccountNumber( accountsDto.getAccountNumber() );
        accounts.setAccountType( accountsDto.getAccountType() );
        accounts.setBranchAddress( accountsDto.getBranchAddress() );
    }
}
