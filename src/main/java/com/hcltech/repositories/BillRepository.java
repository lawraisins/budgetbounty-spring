package com.hcltech.repositories;

import com.hcltech.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
    // Get all bills for a user
    List<Bill> findByUserUserId(Long userId);

    // Find unpaid bills
    List<Bill> findByBillStatus(String status);

 // Fetch unpaid bills that are due within the next X days
    @Query("SELECT b FROM Bill b WHERE b.billStatus = 'Unpaid' AND b.dueDate BETWEEN :today AND :futureDate")
    List<Bill> findUnpaidBillsDueWithinDays(@Param("today") LocalDate today, @Param("futureDate") LocalDate futureDate);

    // Find recurring bills for a user
    List<Bill> findByUserUserIdAndRecurringTrue(Long userId);
    
    // Fetch unpaid bills for user (For make-payment dropdown)
    @Query("SELECT b FROM Bill b WHERE b.user.userId = :userId AND b.billStatus = 'Unpaid'")
    List<Bill> findUnpaidBillsByUserId(@Param("userId") Long userId);

    // Fetch only bill names for unpaid bills (Optional - If Frontend needs it separately)
    @Query("SELECT b.billName FROM Bill b WHERE b.user.userId = :userId AND b.billStatus = 'Unpaid'")
    List<String> findUnpaidBillNamesByUserId(@Param("userId") Long userId);
}
