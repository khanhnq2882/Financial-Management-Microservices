package com.khanhnq.accounts.mapper;

import com.khanhnq.accounts.dto.CustomerDto;
import com.khanhnq.accounts.entity.Customer;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-23T08:19:26+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer mapToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setName( customerDto.getName() );
        customer.setEmail( customerDto.getEmail() );
        customer.setMobileNumber( customerDto.getMobileNumber() );

        return customer;
    }

    @Override
    public CustomerDto mapToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.name( customer.getName() );
        customerDto.email( customer.getEmail() );
        customerDto.mobileNumber( customer.getMobileNumber() );

        return customerDto.build();
    }

    @Override
    public void updateCustomer(Customer customer, CustomerDto customerDto) {
        if ( customerDto == null ) {
            return;
        }

        customer.setName( customerDto.getName() );
        customer.setEmail( customerDto.getEmail() );
        customer.setMobileNumber( customerDto.getMobileNumber() );
    }
}
