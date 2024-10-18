package com.fintect.MobileMoneyAccountMs.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(
            description = "firstname of the customer", example = "Mobile Money"
    )
    @NotEmpty(message = "firstname can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String firstname;

    @Schema(
            description = "lastname of the customer", example = "Mobile Money"
    )
    @NotEmpty(message = "lastname can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer lastname should be between 5 and 30")
    private String lastname;

    @Schema(
            description = "state of the customer", example = "Mobile Money"
    )
    @NotEmpty(message = "state can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer state should be between 5 and 30")
    private String state;

    @Schema(
            description = "nationality of the customer", example = "Mobile Money"
    )
    @NotEmpty(message = "nationality can not be a null or empty")
    @Size(min = 5, max = 30, message = "The length of the nationality name should be between 5 and 30")
    private String nationality;

    @Schema(
            description = "Email address of the customer", example = "tutor@monilemoney.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
    @Pattern(regexp = "(^$|[0-9]{10}|[0-11]{10}|[0-13]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountsDto getAccountsDto() {
        return accountsDto;
    }

    public void setAccountsDto(AccountsDto accountsDto) {
        this.accountsDto = accountsDto;
    }
}
