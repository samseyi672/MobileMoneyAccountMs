package com.fintect.MobileMoneyAccountMs.service;


import com.fintect.MobileMoneyAccountMs.dto.request.AccountsDto;
import com.fintect.MobileMoneyAccountMs.dto.request.CustomerDto;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {

    void createAccount(CustomerDto customerDto) ;

    CustomerDto fetchCustomerDetails(String mobileNumber);

    List<AccountsDto> fetchAccountByCustomerId(String customerId);

    boolean updateAccount(CustomerDto customerDto);
    AccountsDto fetchAccountByAccountNumber(String accountNumber);

    void createExtraAccount(String customerId);

    BigDecimal getAccountBalance(String sourceAccount);
}
