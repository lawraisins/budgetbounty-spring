package com.hcltech.services;

import com.hcltech.models.BankAccount;
import com.hcltech.models.User;
import com.hcltech.repositories.BankAccountRepository;
import com.hcltech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve all bank accounts for a user (for dropdown).
     */
    public List<BankAccount> getBankAccountsForUser(Long userId) {
        return bankAccountRepository.findByUserUserId(userId);
    }

    /**
     * Add a new bank account via Add Payments form.
     */
    public String addBankAccount(Long userId, String bankAccountNumber) {
        // Validate if user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return "User not found!";
        }

        // Check if the bank account already exists for this user
        if (bankAccountRepository.existsByUserUserIdAndBankAccountNumber(userId, bankAccountNumber)) {
            return "Bank account already exists!";
        }

        // Create new bank account and save
        BankAccount newAccount = new BankAccount();
        newAccount.setUser(userOptional.get());
        newAccount.setBankAccountNumber(bankAccountNumber);
        bankAccountRepository.save(newAccount);

        return "Bank account added successfully!";
    }
}
