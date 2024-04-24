package com.khanhnq.accounts.repository;

import com.khanhnq.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findAccountsByMobileNumber(String mobileNumber);
}
