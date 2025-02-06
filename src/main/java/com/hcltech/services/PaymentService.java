package com.hcltech.services;

import com.hcltech.models.Bill;
import com.hcltech.models.Payment;
import com.hcltech.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        LocalDate startDate = LocalDate.now().minusDays(days); // Updated to use LocalDate
        return paymentRepository.findPaymentsWithinLastDays(startDate);
    }

    /**
     * Make a payment and update bill status.
     */
    public Payment makePayment(Payment payment) {
        Bill bill = billService.getBillById(payment.getBill().getBillId()); 

        if (bill == null) {
            throw new IllegalArgumentException("Bill not found.");
        }

        if ("Paid".equalsIgnoreCase(bill.getBillStatus())) {
            throw new IllegalStateException("This bill has already been paid.");
        }

        // Ensure payment amount matches bill amount
        if (payment.getPaymentAmount() != bill.getAmount()) {
            throw new IllegalArgumentException("Payment amount does not match bill amount.");
        }

        // Mark bill as paid
        bill.setBillStatus("Paid");
        billService.createBill(bill);  // Save updated bill

        // Set payment date to now
        payment.setPaymentDate(LocalDate.now());

        // Credit user with 10 points
        authService.updateUserPoints(payment.getUser().getUserId(), 10);

        return paymentRepository.save(payment);
    }
}
