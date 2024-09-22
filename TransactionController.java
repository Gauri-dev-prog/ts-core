package com.example.MyProject;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private CustomerTransactionRepository transactionRepository;

    @PostMapping
    public ResponseEntity<CustomerTransaction> addTransaction(@Valid @RequestBody CustomerTransaction transaction) {
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable int customerId) {
        return ResponseEntity.ok(transactionRepository.findByCustomerId(customerId));
    }
}
