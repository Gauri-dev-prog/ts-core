package com.example.MyProject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerTransactionService {

    @Autowired
    private CustomerTransactionRepository transactionRepository;

    public List<CustomerTransaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Repository
    public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
        List<CustomerTransaction> findByCustomerId(Long customerId);
    }

    public Optional<CustomerTransaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public CustomerTransaction addTransaction(CustomerTransaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    


    public CustomerTransaction updateTransaction(Long transactionId, CustomerTransaction updatedTransaction) {
        Optional<CustomerTransaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()) {
            CustomerTransaction existingTransaction = transaction.get();
            // Update necessary fields
            existingTransaction.setAmount(updatedTransaction.getAmount());
            existingTransaction.setSpentDetails(updatedTransaction.getSpentDetails());
            return transactionRepository.save(existingTransaction);
        } else {
            throw new RuntimeException("Transaction not found");
        }
    }

    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

	public List<CustomerTransaction> getTransactionsByCustomerId(Long customerId) {

		return null;
	}
}
