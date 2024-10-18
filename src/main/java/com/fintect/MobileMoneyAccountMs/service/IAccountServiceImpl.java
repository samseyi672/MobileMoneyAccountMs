package com.fintect.MobileMoneyAccountMs.service;


import com.fintect.MobileMoneyAccountMs.mapper.AccountsMapper;
import com.fintect.MobileMoneyAccountMs.dto.request.AccountsDto;
import com.fintect.MobileMoneyAccountMs.dto.request.CustomerDto;
import com.fintect.MobileMoneyAccountMs.entities.Account;
import com.fintect.MobileMoneyAccountMs.entities.Customer;
import com.fintect.MobileMoneyAccountMs.exception.ResourceNotFoundException;
import com.fintect.MobileMoneyAccountMs.mapper.CustomerMapper;
import com.fintect.MobileMoneyAccountMs.repository.AccountRepository;
import com.fintect.MobileMoneyAccountMs.repository.CustomerRepository;
import com.fintect.MobileMoneyAccountMs.utils.AppUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class IAccountServiceImpl implements IAccountService{

    private AccountRepository accountRepository ;

    private CustomerRepository customerRepository ;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer1 = createCustomer(customerDto);
        generateCustomerAccount(customerDto, customer1,accountRepository);
    }

    @Override
    public CustomerDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("customer","mobilenumber",mobileNumber)) ;
        //map customer to customerdto
        CustomerDto customerDto =  new CustomerDto() ;
        customerDto.setEmail(customerDto.getEmail());
        customerDto.setLastname(customerDto.getLastname());
        customerDto.setNationality(customer.getNationality());
        customerDto.setState(customer.getState());
        customerDto.setAccountsDto(null);
        return customerDto;
    }

    @Override
    public List<AccountsDto> fetchAccountByCustomerId(String customerId) {
        List<Account> accounts =accountRepository.findByCustomerId(customerId).orElseThrow(() -> new ResourceNotFoundException("Account","customerId",customerId)) ;
        List<AccountsDto> accountsDtos =  accounts.parallelStream().map(account -> {
            AccountsDto accountsDto  = new AccountsDto() ;
            accountsDto.setAccountNumber(account.getAccountNumber());
            accountsDto.setAccountTier(account.getAccountTier());
            accountsDto.setBranchAddress(account.getBranchAddress());
            return accountsDto ;
        }).collect(Collectors.toList()) ;
        return accountsDtos;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Account accounts = accountRepository.findByAccountNumber(String.valueOf(accountsDto.getAccountNumber())).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);
            Long customerId = Long.parseLong(accounts.getCustomerId());
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public AccountsDto fetchAccountByAccountNumber(String accountNumber) {
       Account account  = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException("accountNumber","accountNumber",accountNumber));
       AccountsDto accountsDto  = new AccountsDto() ;
       accountsDto.setAccountTier(account.getAccountTier());
       accountsDto.setAccountNumber(account.getAccountNumber());
       accountsDto.setBranchAddress(account.getBranchAddress());
       return accountsDto;
    }

    @Override
    public void createExtraAccount(String customerId) {
       Customer customer  = customerRepository.findByCustomerAccountID(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer","customerAccountID",customerId));
       CustomerDto customerDto  = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
       generateCustomerAccount(customerDto,customer,accountRepository);
      }

    @Override
    public BigDecimal getAccountBalance(String sourceAccount) {
        Account account = accountRepository.findByAccountNumber(sourceAccount).orElseThrow(() -> new ResourceNotFoundException("AccountNumber","sourceAccount",sourceAccount));
        return account.getBookBalance() ;
      }

    private static void generateCustomerAccount(CustomerDto customerDto, Customer customer1,AccountRepository accountRepository) {
        AccountsDto accountsDto  = customerDto.getAccountsDto() ;
        Account account  = new Account() ;
        account.setAccountNumber(Long.parseLong(AppUtils.generateRequestID(10))); //generate accountnumber ;
        account.setBranchAddress(accountsDto.getBranchAddress());
        account.setCustomerId(customer1.getCustomerAccountID());
        account.setAccountTier(account.getAccountTier());
        accountRepository.save(account);
    }

    private Customer createCustomer(CustomerDto customerDto) {
        Customer customer =   new Customer() ;
        customer.setEmail(customer.getEmail());
        customer.setFirstname(customer.getFirstname());
        customer.setLastname(customerDto.getLastname());
        customer.setState(customerDto.getState());
        customer.setNationality(customer.getNationality());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setCustomerAccountID(AppUtils.generateRequestID(5));
        Customer  customer1 =customerRepository.save(customer) ;
        return customer1;
    }
}




