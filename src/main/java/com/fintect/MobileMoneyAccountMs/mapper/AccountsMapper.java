package com.fintect.MobileMoneyAccountMs.mapper;


import com.fintect.MobileMoneyAccountMs.dto.request.AccountsDto;
import com.fintect.MobileMoneyAccountMs.entities.Account;

public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Account accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountTier(accounts.getAccountTier());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Account mapToAccounts(AccountsDto accountsDto, Account accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountTier(accountsDto.getAccountTier());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
