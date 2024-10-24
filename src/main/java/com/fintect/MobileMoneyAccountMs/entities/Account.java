package com.fintect.MobileMoneyAccountMs.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Entity
//@Getter
//@Setter
@Table(name="accounts")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends  BaseEntity {

    @Column(name="account_number")
   // @Id
    private Long accountNumber;

    @Column(name="account_tier")
    private String accountTier;

    @Column(name="accountApprovalStatus")
    private boolean accountApprovalStatus; // this will be approved after all the kyc are submitted

    @Column(name="branch_address")
    private String branchAddress;

    @Column(name="balance")
    private BigDecimal bookBalance;

    @Column(name="customerId")
    private String customerId; // this detemrines account ownership

    public BigDecimal getBookBalance() {
        return bookBalance;
    }

    public void setBookBalance(BigDecimal bookBalance) {
        this.bookBalance = bookBalance;
    }

    public boolean isAccountApprovalStatus() {
        return accountApprovalStatus;
    }

    public void setAccountApprovalStatus(boolean accountApprovalStatus) {
        this.accountApprovalStatus = accountApprovalStatus;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountTier() {
        return accountTier;
    }

    public void setAccountTier(String accountTier) {
        this.accountTier = accountTier;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
