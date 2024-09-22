package com.example.MyProject;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private Long customerId;
    private double amount;
    private LocalDate date;
	private String spentDetails;
    
    public Long getTransactionId() {
        return transactionId;
    }

   
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

   
    public double getAmount() {
        return amount;
    }

    
    public void setAmount(double amount) {
        this.amount = amount;
    }

   
    public LocalDate getDate() {
        return date;
    }

    
    public void setDate(LocalDate date) {
        this.date = date;
    }


    public String getSpentDetails() {
        return spentDetails;
    }

    public void setSpentDetails(String spentDetails) {
        this.spentDetails = spentDetails;
    }
}
