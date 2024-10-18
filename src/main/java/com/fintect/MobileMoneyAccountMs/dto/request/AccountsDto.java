package com.fintect.MobileMoneyAccountMs.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

//@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    @Schema(
            description = "Account Number of MobileMeney account", example = "3454433243"
    )
    private Long accountNumber;

    @NotEmpty(message = "AccountTier can not be a null or empty")
    @Schema(
            description = "Account tier of MobileMoney account", example = "Savings"
    )
    private String accountTier;

    @NotEmpty(message = "BranchAddress can not be a null or empty")
    @Schema(
            description = "MobileMoney branch address", example = "123 NewYork"
    )
    private String branchAddress;

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
}





