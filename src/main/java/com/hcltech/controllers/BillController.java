package com.hcltech.controllers;

import com.hcltech.models.Bill;
import com.hcltech.models.User;
import com.hcltech.services.AuthService;
import com.hcltech.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Enable CORS for React frontend
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;
    
    @Autowired
    private AuthService authService;

    /**
     * Retrieve all bills for a user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bill>> getAllBillsForUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(billService.getAllBillsForUser(userId));
    }

    /**
     * Retrieve a single bill by ID.
     */
    @GetMapping("/{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable("billId") Long billId) {
        return ResponseEntity.ok(billService.getBillById(billId));
    }

    /**
     * Retrieve unpaid bills that are due within the next 10 days for a specific user.
     */
    @GetMapping("/unpaid-due-10days/{userId}")
    public ResponseEntity<List<Bill>> getUnpaidBillsDueWithin10Days(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(billService.getUnpaidBillsDueWithin10Days(userId));
    }

    /**
     * Get recurring bills for a user.
     */
    @GetMapping("/recurring/{userId}")
    public ResponseEntity<List<Bill>> getRecurringBills(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(billService.getRecurringBills(userId));
    }

    /**
     * Retrieve unpaid bills for a user.
     */
    @GetMapping("/unpaid/{userId}")
    public ResponseEntity<List<Bill>> getUnpaidBillsForUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(billService.getUnpaidBillsForUser(userId));
    }

//    /**
//     * Create a new bill. (pre-admin method ignore for now)
//     */
//    @PostMapping("/create")
//    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
//        return ResponseEntity.ok(billService.createBill(bill));
//    }

    /**
     * Update an existing bill.
     */
    @PutMapping("/update/{billId}")
    public ResponseEntity<Bill> updateBill(@PathVariable("billId") Long billId, @RequestBody Bill updatedBill) {
        return ResponseEntity.ok(billService.updateBill(billId, updatedBill));
    }

    /**
     * Delete a bill.
     */
    @DeleteMapping("/delete/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable("billId") Long billId) {
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
    
    /**
     * Retrieve all bills (admin access).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    /**
     * Get all users for dropdown selection in Add Bill Form (admin access).
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    /**
     * Create a new bill and assign it to a user.
     */
//    @PostMapping("/create")
//    public ResponseEntity<?> createBill(@RequestBody Bill bill, @RequestParam Long userId) {
//        Optional<User> userOpt = authService.getUserById(userId);
//        if (userOpt.isEmpty()) {
//            return ResponseEntity.badRequest().body("User not found.");
//        }
//
//        bill.setUser(userOpt.get());
//        Bill savedBill = billService.createBill(bill);
//        return ResponseEntity.ok(savedBill);
//    }

    
    //version2 (working)
    @PostMapping("/create")
    public ResponseEntity<?> createBill(@RequestBody Bill billRequest) {
        Optional<User> userOpt = authService.getUserById(billRequest.getUser().getUserId());
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }

        billRequest.setUser(userOpt.get());
        Bill savedBill = billService.createBill(billRequest);
        return ResponseEntity.ok(savedBill);
    }
}
