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

    /**
     * Get all payments for a user.
     */
    public List<Payment> getPaymentsForUser(Long userId) {
        return paymentRepository.findByUserUserId(userId);
    }

    /**
     * Get payment history (paid bills).
     */
    public List<Payment> getPaymentHistory(Long userId) {
        return paymentRepository.findPaidPaymentsForUser(userId);
    }

    /**
     * Make a payment and update bill status.
     */
    public Payment makePayment(Payment payment) {
        billService.markBillAsPaid(payment.getBill().getBillId());
        payment.setPaymentDateTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}
