package com.khanhnq.accounts.service;

import com.khanhnq.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
