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

    // Get all bills for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getAllBillsForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(billService.getAllBillsForUser(userId));
    }

    // Get a single bill by ID
    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId) {
        return ResponseEntity.ok(billService.getBillById(billId));
    }

    // Mark a bill as paid
    @PostMapping("/{billId}/pay")
    public ResponseEntity<Bill> markBillAsPaid(@PathVariable Long billId) {
        return ResponseEntity.ok(billService.markBillAsPaid(billId));
    }

    // Create a new bill
    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(billService.createBill(bill));
    }

    // Delete a bill
    @DeleteMapping("/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.ok("Bill deleted successfully.");
    }
}
