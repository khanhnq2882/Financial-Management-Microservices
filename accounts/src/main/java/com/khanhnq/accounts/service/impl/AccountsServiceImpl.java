package com.khanhnq.accounts.service.impl;

import com.khanhnq.accounts.constants.AccountsConstants;
import com.khanhnq.accounts.dto.AccountsDto;
import com.khanhnq.accounts.dto.CustomerDto;
import com.khanhnq.accounts.entity.Accounts;
import com.khanhnq.accounts.entity.Customer;
import com.khanhnq.accounts.exception.CustomerAlreadyExistException;
import com.khanhnq.accounts.exception.ResourceNotFoundException;
import com.khanhnq.accounts.mapper.AccountsMapper;
import com.khanhnq.accounts.mapper.CustomerMapper;
import com.khanhnq.accounts.repository.AccountsRepository;
import com.khanhnq.accounts.repository.CustomerRepository;
import com.khanhnq.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> checkCustomer = customerRepository.findAccountsByMobileNumber(customerDto.getMobileNumber());
        if (checkCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with given mobile number " +customerDto.getMobileNumber());
        }
        Customer customer = customerRepository.save(CustomerMapper.CUSTOMER_MAPPER.mapToCustomer(customerDto));
        customer.setCreatedAt(LocalDateTime.now());
        customerRepository.save(customer);
        accountsRepository.save(createNewAccount(customer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Optional<Customer> customer = customerRepository.findAccountsByMobileNumber(mobileNumber);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "mobile number", mobileNumber);
        }
        Optional<Accounts> accounts = accountsRepository.findAccountsByCustomerId(customer.get().getCustomerId());
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Account", "customer ID", customer.get().getCustomerId().toString());
        }
        AccountsDto accountsDto = AccountsMapper.ACCOUNTS_MAPPER.mapToAccountsDto(accounts.get());
        CustomerDto customerDto = CustomerMapper.CUSTOMER_MAPPER.mapToCustomerDto(customer.get());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (Objects.nonNull(accountsDto)) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "account number", accountsDto.getAccountNumber().toString()));
            AccountsMapper.ACCOUNTS_MAPPER.updateAccounts(accounts, accountsDto);
            accountsRepository.save(accounts);
            Customer customer = customerRepository.findAccountsByMobileNumber(customerDto.getMobileNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", customerDto.getMobileNumber()));
            CustomerMapper.CUSTOMER_MAPPER.updateCustomer(customer, customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findAccountsByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

}
