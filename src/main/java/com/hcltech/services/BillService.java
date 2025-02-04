package com.hcltech.services;

import com.hcltech.models.Bill;
import com.hcltech.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    /**
     * Retrieve all bills for a user.
     */
    public List<Bill> getAllBillsForUser(Long userId) {
        return billRepository.findByUserUserId(userId);
    }

    /**
     * Get upcoming bills (due after today).
     */
    public List<Bill> getUpcomingBills(Long userId) {
        return billRepository.findUpcomingBills(userId, LocalDate.now());
    }

    /**
     * Retrieve recurring bills.
     */
    public List<Bill> getRecurringBills(Long userId) {
        return billRepository.findRecurringBills(userId);
    }

    /**
     * Mark a bill as paid.
     */
    public Bill markBillAsPaid(Long billId) {
        Optional<Bill> billOptional = billRepository.findById(billId);
        if (billOptional.isPresent()) {
            Bill bill = billOptional.get();
            bill.setBillStatus("Paid");
            return billRepository.save(bill);
        }
        return null;
    }
}
