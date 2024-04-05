package com.pegbeer.accounts.mapper;

import com.pegbeer.accounts.dto.AccountsDto;
import com.pegbeer.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto toAccountsDto(Accounts accounts){
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts toAccounts(AccountsDto accountsDto){
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
