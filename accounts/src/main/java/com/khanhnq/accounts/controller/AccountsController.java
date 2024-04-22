package com.khanhnq.accounts.controller;

import com.khanhnq.accounts.constants.AccountsConstants;
import com.khanhnq.accounts.dto.CustomerDto;
import com.khanhnq.accounts.dto.ErrorResponseDto;
import com.khanhnq.accounts.dto.ResponseDto;
import com.khanhnq.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class AccountsController {
    private final IAccountsService accountsService;

    public AccountsController(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Accounts"
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
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        ResponseDto responseDto = ResponseDto.builder()
                .statusCode(AccountsConstants.STATUS_201)
                .statusMsg(AccountsConstants.MESSAGE_201)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to fetch Customer & Accounts details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
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
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam String mobileNumber) {
        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer & Account details based on a account number"
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
    @PostMapping("/update")
    public ResponseEntity<ResponseDto> fetchAccount(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        ResponseDto responseDto;
        if (isUpdated) {
            responseDto = ResponseDto.builder()
                    .statusCode(AccountsConstants.STATUS_200)
                    .statusMsg(AccountsConstants.MESSAGE_200)
                    .build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        responseDto = ResponseDto.builder()
                .statusCode(AccountsConstants.STATUS_417)
                .statusMsg(AccountsConstants.MESSAGE_417_UPDATE)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.EXPECTATION_FAILED);
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer & Account details based on a mobile number"
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber) {
        boolean isUpdated = accountsService.deleteAccount(mobileNumber);
        ResponseDto responseDto;
        if (isUpdated) {
            responseDto = ResponseDto.builder()
                    .statusCode(AccountsConstants.STATUS_200)
                    .statusMsg(AccountsConstants.MESSAGE_200)
                    .build();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        responseDto = ResponseDto.builder()
                .statusCode(AccountsConstants.STATUS_417)
                .statusMsg(AccountsConstants.MESSAGE_417_DELETE)
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.EXPECTATION_FAILED);
    }

}
