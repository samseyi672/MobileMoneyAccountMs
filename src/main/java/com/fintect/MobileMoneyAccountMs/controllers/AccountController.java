package com.fintect.MobileMoneyAccountMs.controllers;


import com.fintect.MobileMoneyAccountMs.constants.AccountsConstants;
import com.fintect.MobileMoneyAccountMs.dto.request.AccountsContactInfoDto;
import com.fintect.MobileMoneyAccountMs.dto.request.AccountsDto;
import com.fintect.MobileMoneyAccountMs.dto.request.CustomerDto;
import com.fintect.MobileMoneyAccountMs.dto.response.ErrorResponseDto;
import com.fintect.MobileMoneyAccountMs.dto.response.ResponseDto;
import com.fintect.MobileMoneyAccountMs.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author samson
 * This controller is for accounts management
 */

@Tag(
        name = "CRUD REST APIs for Accounts in MobileMoney",
        description = "CRUD REST APIs in MobileMOney to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path="/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
//@AllArgsConstructor
public class AccountController {

   private IAccountService accountService ;

   @Value("${build.version}")
    private String buildVersion="1.0.0";

    @Autowired
    private Environment environment;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account inside MobileMoney"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }


    @Operation(
            summary = "AccountBalance REST API",
            description = "REST API to get Balance MobileMoney"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/accountBalance")
    public ResponseEntity<BigDecimal> getAccountBalance(@Valid @RequestBody String sourceAccount) {
        BigDecimal balance = accountService.getAccountBalance(sourceAccount);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(balance);
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new extra Customer &  Account inside MobileMoney.Customer details already exists"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/createExtraAccount/{customerId}")
    public ResponseEntity<ResponseDto> createExtraAccount(@PathVariable(name = "customerId") String  customerId) {
        accountService.createExtraAccount(customerId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchAccountDetails")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        CustomerDto customerDto = accountService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Fetch Account By Customer Id REST API",
            description = "REST API to fetch all customrs Account details based on a customerid"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch/{customerId}")
    public ResponseEntity<List<AccountsDto>> fetchAccountByCustomerId(@PathVariable @NotEmpty(message = "customerId cannot be empty or null") String customerId) {
        List<AccountsDto> accountsDtos = accountService.fetchAccountByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(accountsDtos);
    }

    @Operation(
            summary = "Fetch Account By AccountNumber REST API",
            description = "REST API to fetch AccountNumber details based on a AccountNumber"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchAccountByAccountNumber/{accountNumber}")
    public ResponseEntity<AccountsDto> fetchAccountByAccountNumber(@PathVariable @Pattern(regexp="(^$|[0-9]{10})",message = "accountNumber must be 10 digita") String accountNumber) {
         AccountsDto accountsDto = accountService.fetchAccountByAccountNumber(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountsDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
}









