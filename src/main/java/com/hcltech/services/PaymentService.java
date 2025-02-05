package com.hcltech.services;

import com.hcltech.models.Bill;
import com.hcltech.models.Payment;
import com.hcltech.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BillService billService;
    
    @Autowired
    private AuthService authService; // Updated to use AuthService for points management

    /**
     * Get all payments for a user.
     */
    public List<Payment> getPaymentsForUser(Long userId) {
        return paymentRepository.findByUserUserId(userId);
    }

    /**
     * Get payment history (only paid bills).
     */
    public List<Payment> getPaymentHistory(Long userId) {
        return paymentRepository.findPaidPaymentsForUser(userId);
    }

    /**
     * Get payments made from a specific bank account.
     */
    public List<Payment> getPaymentsByBankAccount(Long bankAccountId) {
        return paymentRepository.findByBankAccountBankAccountId(bankAccountId);
    }

    /**
     * Get all payments made within the last X days.
     */
    public List<Payment> getPaymentsWithinLastDays(int days) {
        LocalDateTime startDate = LocalDateTime.now().minusDays(days); // Compute start date
        return paymentRepository.findPaymentsWithinLastDays(startDate);
    }

    /**
     * Make a payment and update bill status.
     */
    public Payment makePayment(Payment payment) {
        Bill bill = billService.getBillById(payment.getBill().getBillId()); 
        
        if (bill == null) {
            throw new IllegalArgumentException("Bill not found.");
        } //newly added, check here if got error

        // Ensure the bill isn't already paid before proceeding
        if ("Paid".equalsIgnoreCase(bill.getBillStatus())) {
            throw new IllegalStateException("Bill has already been paid.");
        }

        // Mark the bill as paid
        billService.markBillAsPaid(bill.getBillId());

        // Set payment date to now
        payment.setPaymentDateTime(LocalDateTime.now());

        // Earn 10 points for the payment
        authService.updateUserPoints(payment.getUser().getUserId(), 10);

        return paymentRepository.save(payment);
    }
}
