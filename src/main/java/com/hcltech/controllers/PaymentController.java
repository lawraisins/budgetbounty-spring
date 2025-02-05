package com.hcltech.controllers;

import com.hcltech.models.Payment;
import com.hcltech.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Retrieve all payments for a user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsForUser(userId));
    }

    /**
     * Get payment history (only paid bills).
     */
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Payment>> getPaymentHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentHistory(userId));
    }

    /**
     * Get payments made from a specific bank account.
     */
    @GetMapping("/bank-account/{bankAccountId}")
    public ResponseEntity<List<Payment>> getPaymentsByBankAccount(@PathVariable Long bankAccountId) {
        return ResponseEntity.ok(paymentService.getPaymentsByBankAccount(bankAccountId));
    }

    /**
     * Get all payments made within the last X days.
     */
    @GetMapping("/recent/{days}")
    public ResponseEntity<List<Payment>> getPaymentsWithinLastDays(@PathVariable int days) {
        return ResponseEntity.ok(paymentService.getPaymentsWithinLastDays(days));
    }

    /**
     * Make a payment and update bill status.
     */
    @PostMapping("/make-payment")
    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.makePayment(payment));
    }
}
