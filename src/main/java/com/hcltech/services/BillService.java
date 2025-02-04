package com.hcltech.services;

import com.hcltech.models.Bill;
import com.hcltech.repositories.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        LocalDate nextMonth = today.plusDays(30);
        return billRepository.findUpcomingDueBills(today, nextMonth);
    }

    /**
     * Get a single bill by ID.
     */
    public Bill getBillById(Long billId) {
        return billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Bill with ID " + billId + " not found"));
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
        Bill bill = getBillById(billId); // Fetch bill or throw error if not found

        if ("Paid".equalsIgnoreCase(bill.getBillStatus())) {
            throw new IllegalStateException("Bill with ID " + billId + " has already been paid.");
        }

        bill.setBillStatus("Paid");
        return billRepository.save(bill);
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
        Bill bill = getBillById(billId);

        bill.setAmount(updatedBill.getAmount());
        bill.setDueDate(updatedBill.getDueDate());
        bill.setRecurring(updatedBill.isRecurring());
        bill.setBillStatus(updatedBill.getBillStatus());

        return billRepository.save(bill);
    }

    /**
     * Delete a bill.
     */
    public void deleteBill(Long billId) {
        if (!billRepository.existsById(billId)) {
            throw new IllegalArgumentException("Bill with ID " + billId + " not found.");
        }
        billRepository.deleteById(billId);
    }
}
