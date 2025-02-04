package com.hcltech.services;

import com.hcltech.models.BankAccount;
import com.hcltech.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    /**
     * Retrieve all bank accounts for a user.
     */
    public List<BankAccount> getBankAccountsForUser(Long userId) {
        return bankAccountRepository.findByUserUserId(userId);
    }

    /**
     * Add a new bank account.
     */
    public BankAccount addBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    /**
     * Delete a bank account.
     */
    public void deleteBankAccount(Long bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }
}
