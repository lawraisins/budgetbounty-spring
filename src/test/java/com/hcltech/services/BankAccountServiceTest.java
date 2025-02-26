package com.hcltech.services;

import com.hcltech.models.BankAccount;
import com.hcltech.models.User;
import com.hcltech.repositories.BankAccountRepository;
import com.hcltech.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceTest {

    @InjectMocks
    private BankAccountService bankAccountService;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testAddBankAccount_Success() {
        // Step 1: Prepare test data
        Long userId = 1L;
        String accountNumber = "9876543210";

        User user = new User();
        user.setUserId(userId);

        // Step 2: Mock behavior
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bankAccountRepository.existsByUserUserIdAndBankAccountNumber(userId, accountNumber)).thenReturn(false);

        // Step 3: Call the service method
        String result = bankAccountService.addBankAccount(userId, accountNumber);

        // Step 4: Verify the outcome
        assertEquals("Bank account added successfully!", result);
        verify(bankAccountRepository, times(1)).save(any(BankAccount.class));
    }

    @Test
    public void testAddBankAccount_AlreadyExists() {
        // Step 1: Prepare test data
        Long userId = 1L;
        String accountNumber = "1234567890";

        User user = new User();
        user.setUserId(userId);

        // Step 2: Mock repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bankAccountRepository.existsByUserUserIdAndBankAccountNumber(userId, accountNumber)).thenReturn(true);

        // Step 3: Call the service method
        String response = bankAccountService.addBankAccount(userId, accountNumber);

        // Step 4: Assert the expected error message
        assertEquals("Bank account already exists!", response);

        // Step 5: Ensure that the save method is never called in this scenario
        verify(bankAccountRepository, never()).save(any(BankAccount.class));
    }

    @Test
    public void testAddBankAccount_UserNotFound() {
        // Step 1: Prepare test data
        Long userId = 99L; // Non-existing user
        String accountNumber = "5555555555";

        // Step 2: Mock repository behavior
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Step 3: Call the service method
        String response = bankAccountService.addBankAccount(userId, accountNumber);

        // Step 4: Assert the expected error message
        assertEquals("User not found!", response);

        // Step 5: Ensure that no database changes were made
        verify(bankAccountRepository, never()).save(any(BankAccount.class));
    }
}
