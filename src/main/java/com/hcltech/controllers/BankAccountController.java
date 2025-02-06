package com.hcltech.controllers;

import com.hcltech.models.BankAccount;
import com.hcltech.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for React frontend
@RequestMapping("/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    /**
     * Get all bank accounts for a user (Dropdown selection)
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<BankAccount>> getBankAccountsForUser(@PathVariable Long userId) {
        List<BankAccount> accounts = bankAccountService.getBankAccountsForUser(userId);
        return ResponseEntity.ok(accounts);
    }

    /**
     * Add a new bank account (from Add Payments form)
     */
    @PostMapping("/add")
    public ResponseEntity<String> addBankAccount(@RequestParam Long userId, @RequestParam String bankAccountNumber) {
        String response = bankAccountService.addBankAccount(userId, bankAccountNumber);
        return ResponseEntity.ok(response);
    }

    /**
     * Check if a user already has a specific bank account
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkBankAccountExists(@RequestParam Long userId, @RequestParam String bankAccountNumber) {
        boolean exists = bankAccountService.checkBankAccountExists(userId, bankAccountNumber);
        return ResponseEntity.ok(exists);
    }
}
