package com.hcltech.repositories;

import com.hcltech.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    
    // Get bills by user ID
    List<Bill> findByUserUserId(Long userId);

    // Find unpaid bills
    List<Bill> findByBillStatus(String status);

    // Find all upcoming due bills (unpaid) within the next X days
    @Query("SELECT b FROM Bill b WHERE b.billStatus = 'Unpaid' AND b.dueDate BETWEEN CURRENT_DATE AND CURRENT_DATE + :days")
    List<Bill> findUpcomingDueBills(int days);
}
