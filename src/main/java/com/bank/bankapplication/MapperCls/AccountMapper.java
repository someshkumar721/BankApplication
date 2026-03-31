package com.bank.bankapplication.MapperCls;

import com.bank.bankapplication.Dto.AccountDto;
import com.bank.bankapplication.Entity.AccountsEntity;

public class AccountMapper {

    public static AccountDto mapToAccountsDto(AccountsEntity accounts, AccountDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNO());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static AccountsEntity mapToAccounts(AccountDto accountsDto, AccountsEntity accounts) {
        accounts.setAccountNO(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
