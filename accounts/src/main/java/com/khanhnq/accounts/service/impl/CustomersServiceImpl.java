package com.khanhnq.accounts.service.impl;

import com.khanhnq.accounts.dto.AccountsDto;
import com.khanhnq.accounts.dto.CardsDto;
import com.khanhnq.accounts.dto.CustomerDetailsDto;
import com.khanhnq.accounts.dto.LoansDto;
import com.khanhnq.accounts.entity.Accounts;
import com.khanhnq.accounts.entity.Customer;
import com.khanhnq.accounts.exception.ResourceNotFoundException;
import com.khanhnq.accounts.mapper.AccountsMapper;
import com.khanhnq.accounts.mapper.CustomerMapper;
import com.khanhnq.accounts.repository.AccountsRepository;
import com.khanhnq.accounts.repository.CustomerRepository;
import com.khanhnq.accounts.service.ICustomersService;
import com.khanhnq.accounts.service.client.CardsFeignClient;
import com.khanhnq.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findAccountsByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findAccountsByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.CUSTOMER_MAPPER.mapToCustomerDetailsDto(customer);
        AccountsDto accountsDto = AccountsMapper.ACCOUNTS_MAPPER.mapToAccountsDto(accounts);
        customerDetailsDto.setAccountsDto(accountsDto);
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        return customerDetailsDto;
    }

}
