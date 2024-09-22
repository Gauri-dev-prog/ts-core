package com.example.MyProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RewardPointsService {

    @Autowired
    private RewardPointsRepository rewardPointsRepository;
    
    @Autowired
    private CustomerTransactionService transactionService;

    public List<RewardPoints> getRewardPointsByCustomer(Long customerId) {
        return rewardPointsRepository.findByCustomerId(customerId);
    }

    public RewardPoints calculateRewardPointsForTransaction(Long customerId, int month, int year) {
        List<CustomerTransaction> transactions = transactionService.getTransactionsByCustomerId(customerId);
        int totalPoints = 0;
        
        for (CustomerTransaction transaction : transactions) {
            if (isTransactionInMonthAndYear(transaction, month, year)) {
                totalPoints += calculatePoints(transaction.getAmount());
            }
        }

        RewardPoints rewardPoints = new RewardPoints();
        rewardPoints.setCustomerId(customerId);
        rewardPoints.setMonth(month);
        rewardPoints.setYear(year);
        rewardPoints.setPoints(totalPoints);

        return rewardPointsRepository.save(rewardPoints);
    }

    private boolean isTransactionInMonthAndYear(CustomerTransaction transaction, int month, int year) {
        LocalDate transactionDate = transaction.getDate(); // Assuming `getDate()` returns a LocalDate
        return transactionDate.getMonthValue() == month && transactionDate.getYear() == year;
    }

    int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
        }
        if (amount > 50) {
            points += (Math.min(amount, 100) - 50);
        }
        return points;
    }
}
