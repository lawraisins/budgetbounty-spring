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
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plusDays(30); // Set 30 days range
        return billRepository.findUpcomingDueBills(today, nextMonth);
    }
    
    /**
     * Get a single bill by ID.
     */
    public Optional<Bill> getBillById(Long billId) {
        return billRepository.findById(billId);
    }

    /**
     * Retrieve recurring bills.
     */
    public List<Bill> getRecurringBills(Long userId) {
        return billRepository.findByUserUserIdAndRecurringTrue(userId);
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
        return null; // Bill not found
    }

    /**
     * Create a new bill.
     */
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    /**
     * Update an existing bill.
     */
    public Bill updateBill(Long billId, Bill updatedBill) {
        Optional<Bill> existingBill = billRepository.findById(billId);
        if (existingBill.isPresent()) {
            Bill bill = existingBill.get();
            bill.setAmount(updatedBill.getAmount());
            bill.setDueDate(updatedBill.getDueDate());
            bill.setRecurring(updatedBill.isRecurring());
            bill.setBillStatus(updatedBill.getBillStatus());
            return billRepository.save(bill);
        }
        return null; // Bill not found
    }

    /**
     * Delete a bill.
     */
    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }
}
