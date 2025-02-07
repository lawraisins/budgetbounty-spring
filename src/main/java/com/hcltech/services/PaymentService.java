package com.hcltech.services;

import com.hcltech.models.BankAccount;
import com.hcltech.models.Bill;
import com.hcltech.models.Payment;
import com.hcltech.models.User;
import com.hcltech.repositories.BankAccountRepository;
import com.hcltech.repositories.BillRepository;
import com.hcltech.repositories.PaymentRepository;
import com.hcltech.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private BillRepository billRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BankAccountRepository bankAccountRepository;

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
    
    @Transactional
    public void processPayment(Long userId, Long billId, String bankAccountNumber) {
        // Fetch bill
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found"));

        // Fetch user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Fetch bank account using the bank account number
        BankAccount bankAccount = bankAccountRepository.findByBankAccountNumberAndUserUserId(bankAccountNumber, userId)
                .orElseThrow(() -> new IllegalArgumentException("Bank account not found"));

        // Mark bill as paid
        bill.setBillStatus("Paid");
        billRepository.save(bill);

        // Credit 10 reward points
        user.setTotalPoints(user.getTotalPoints() + 10);
        userRepository.save(user);

        // Save payment
        Payment payment = new Payment();
        payment.setUser(user);
        payment.setBill(bill);
        payment.setBankAccount(bankAccount);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentAmount(bill.getAmount());
        payment.setPointsEarned(10);
        paymentRepository.save(payment);
    }
}
