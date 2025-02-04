package com.hcltech.repositories;

import com.hcltech.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    
    // Get all bank accounts for a user
    List<BankAccount> findByUserUserId(Long userId);

    // Find by bank account number
    Optional<BankAccount> findByBankAccountNumber(String bankAccountNumber);

    // Check if a bank account exists for a specific user
    boolean existsByUserUserIdAndBankAccountNumber(Long userId, String bankAccountNumber);
}
