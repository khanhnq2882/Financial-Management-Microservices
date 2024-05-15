package com.khanhnq.accounts.mapper;


import com.khanhnq.accounts.dto.CustomerDetailsDto;
import com.khanhnq.accounts.dto.CustomerDto;
import com.khanhnq.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper CUSTOMER_MAPPER = Mappers.getMapper(CustomerMapper.class);
    Customer mapToCustomer(CustomerDto customerDto);
    CustomerDto mapToCustomerDto (Customer customer);
    void updateCustomer (@MappingTarget Customer customer, CustomerDto customerDto);
    CustomerDetailsDto mapToCustomerDetailsDto (Customer customer);
}
