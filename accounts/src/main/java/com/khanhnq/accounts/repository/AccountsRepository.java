package com.khanhnq.accounts.repository;

import com.khanhnq.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findAccountsByCustomerId (Long customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
