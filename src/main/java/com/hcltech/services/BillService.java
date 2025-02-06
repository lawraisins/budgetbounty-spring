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
     * Fetch unpaid bills that are due within the next 10 days for a specific user.
     */
    public List<Bill> getUnpaidBillsDueWithin10Days(Long userId) {
        LocalDate today = LocalDate.now();
        LocalDate futureDate = today.plusDays(10); // Hardcoded 10 days
        return billRepository.findUnpaidBillsDueWithinDaysForUser(userId, today, futureDate);
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
        Bill bill = getBillById(billId);

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

        bill.setBillName(updatedBill.getBillName()); // Added bill name update
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

    // New Method: Fetch unpaid bills for user (For make-payment dropdown)
    public List<Bill> getUnpaidBillsForUser(Long userId) {
        return billRepository.findUnpaidBillsByUserId(userId);
    }

    // New Method: Fetch unpaid bill names only
    public List<String> getUnpaidBillNamesForUser(Long userId) {
        return billRepository.findUnpaidBillNamesByUserId(userId);
    }
}
