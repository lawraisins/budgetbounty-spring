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
        Long userId = 1L;
        String accountNumber = "9876543210";

        User user = new User();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bankAccountRepository.existsByUserUserIdAndBankAccountNumber(userId, accountNumber)).thenReturn(false);

        String result = bankAccountService.addBankAccount(userId, accountNumber);

        assertEquals("Bank account added successfully!", result);
        verify(bankAccountRepository, times(1)).save(any(BankAccount.class));
    }

    @Test
    public void testAddBankAccount_AlreadyExists() {
        Long userId = 1L;
        String accountNumber = "9876543210";

        when(bankAccountRepository.existsByUserUserIdAndBankAccountNumber(userId, accountNumber)).thenReturn(true);

        String result = bankAccountService.addBankAccount(userId, accountNumber);

        assertEquals("Bank account already exists!", result);
        verify(bankAccountRepository, never()).save(any(BankAccount.class));
    }
}
