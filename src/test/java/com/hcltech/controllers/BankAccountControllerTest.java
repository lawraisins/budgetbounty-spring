package com.hcltech.controllers;

import com.hcltech.services.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BankAccountController.class)
@ExtendWith(MockitoExtension.class)
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankAccountService bankAccountService;

    @Test
    public void testAddBankAccount_Success() throws Exception {
        Long userId = 1L;
        String accountNumber = "9876543210";

        when(bankAccountService.addBankAccount(userId, accountNumber)).thenReturn("Bank account added successfully!");

        mockMvc.perform(post("/bank-accounts/add")
                .param("userId", userId.toString())
                .param("bankAccountNumber", accountNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Bank account added successfully!"));
    }

    @Test
    public void testAddBankAccount_Failure() throws Exception {
        Long userId = 1L;
        String accountNumber = "9876543210";

        when(bankAccountService.addBankAccount(userId, accountNumber)).thenReturn("Bank account already exists!");

        mockMvc.perform(post("/bank-accounts/add")
                .param("userId", userId.toString())
                .param("bankAccountNumber", accountNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Bank account already exists!"));
    }
}
