package com.hcltech.controllers;

import com.hcltech.models.Payment;
import com.hcltech.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for React frontend
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Retrieve all payments for a user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsForUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentsForUser(userId));
    }

    /**
     * Get payment history (only paid bills).
     */
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Payment>> getPaymentHistory(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentHistory(userId));
    }

    /**
     * Get payments made from a specific bank account.
     */
    @GetMapping("/bank-account/{bankAccountId}")
    public ResponseEntity<List<Payment>> getPaymentsByBankAccount(@PathVariable("bankAccountId") Long bankAccountId) {
        return ResponseEntity.ok(paymentService.getPaymentsByBankAccount(bankAccountId));
    }

    /**
     * Get all payments made within the last X days.
     */
    @GetMapping("/recent/{days}")
    public ResponseEntity<List<Payment>> getPaymentsWithinLastDays(@PathVariable("days") int days) {
        return ResponseEntity.ok(paymentService.getPaymentsWithinLastDays(days));
    }

    /**
     * Make a payment and update bill status.
     */
//    @PostMapping("/make-payment")
//    public ResponseEntity<Payment> makePayment(@RequestBody Payment payment) {
//        return ResponseEntity.ok(paymentService.makePayment(payment));
//    }
//    
    @PostMapping("/make-payment")
    public ResponseEntity<String> makePayment(@RequestBody Map<String, Object> paymentData) {
        try {
            // Convert userId to Long correctly
            Long userId = Long.parseLong(paymentData.get("userId").toString());

            // Convert billId to Long correctly
            Map<String, Object> billMap = (Map<String, Object>) paymentData.get("bill");
            Long billId = Long.parseLong(billMap.get("billId").toString());

            // Convert bankAccountNumber to String correctly
            Map<String, Object> bankAccountMap = (Map<String, Object>) paymentData.get("bankAccount");
            String bankAccountNumber = bankAccountMap.get("bankAccountNumber").toString();

            // Call service method
            paymentService.processPayment(userId, billId, bankAccountNumber);
            
            return ResponseEntity.ok("Payment successful! 10 reward points credited.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment failed: " + e.getMessage());
        }
    }

}
