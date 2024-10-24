package com.fintect.MobileMoneyAccountMs.repository;


import com.fintect.MobileMoneyAccountMs.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {

    Optional<List<Account>> findByCustomerId(String customerId); // this will customer acount by their customerId
    Optional<Account> findByAccountNumber(Long accountNumber) ;
}
