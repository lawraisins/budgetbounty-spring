package com.hcltech.controllers;

import com.hcltech.models.Bill;
import com.hcltech.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * Retrieve all bills for a user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getAllBillsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(billService.getAllBillsForUser(userId));
    }

    /**
     * Retrieve a single bill by ID.
     */
    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        return ResponseEntity.ok(billService.getBillById(billId));
    }

    /**
     * Get upcoming bills for a user (Due after today).
     */
    @GetMapping("/upcoming/{userId}")
    public ResponseEntity<List<Bill>> getUpcomingBills(@PathVariable Long userId) {
        return ResponseEntity.ok(billService.getUpcomingBills(userId));
    }

    /**
     * Get recurring bills for a user.
     */
    @GetMapping("/recurring/{userId}")
    public ResponseEntity<List<Bill>> getRecurringBills(@PathVariable Long userId) {
        return ResponseEntity.ok(billService.getRecurringBills(userId));
    }

    /**
     * Retrieve unpaid bills for a user.
     */
    @GetMapping("/unpaid/{userId}")
    public ResponseEntity<List<Bill>> getUnpaidBillsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(billService.getUnpaidBillsForUser(userId));
    }

    /**
     * Create a new bill.
     */
    @PostMapping("/create")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(billService.createBill(bill));
    }

    /**
     * Update an existing bill.
     */
    @PutMapping("/update/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long billId, @RequestBody Bill updatedBill) {
        return ResponseEntity.ok(billService.updateBill(billId, updatedBill));
    }

    /**
     * Delete a bill.
     */
    @DeleteMapping("/delete/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.ok("Bill deleted successfully.");
    }

    /**
     * Mark a bill as paid.
     */
    @PutMapping("/mark-paid/{billId}")
    public ResponseEntity<Bill> markBillAsPaid(@PathVariable Long billId) {
        return ResponseEntity.ok(billService.markBillAsPaid(billId));
    }
}
