package com.example.MyProject;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    
    List<Transaction> findByCustomerId(int customerId);
    
    
}
