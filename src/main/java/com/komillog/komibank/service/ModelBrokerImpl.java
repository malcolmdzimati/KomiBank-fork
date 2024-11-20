
package com.komillog.komibank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.komillog.komibank.dao.LoadTransactionData;
import com.komillog.komibank.model.Account;
import com.komillog.komibank.model.Operation;
import com.komillog.komibank.model.User;
import com.komillog.komibank.service.ModelBroker;

import java.util.List;

@Service
public class ModelBrokerImpl implements ModelBroker {

    @Autowired
    private LoadTransactionData operationDao;

    @Autowired
    private RestTemplate restTemplate;

    // API URLs
    private static final String RISK_MODEL_API_URL = "https://api.example.com/risk-model";
    private static final String BEHAVIOR_MODEL_API_URL = "https://api.example.com/behavior-model";

    @Override
    public double calculateAggregatedRisk(Long account) {
        // Fetch the last 1000 transactions
        List<Operation> last1000Transactions = LoadTransactionData.getTransactionHistory(account);

        // Call the two APIs
        double riskScore = callRiskModel(last1000Transactions);
        double behaviorScore = callBehaviorModel(last1000Transactions);

        // Aggregate the risk scores
        return aggregateScores(riskScore, behaviorScore);
    }

    @Override
    public double processNewTransaction(Operation newTransaction, User user) {
        // Call the APIs with the new transaction data
        double riskScore = callRiskModel(List.of(newTransaction));
        double behaviorScore = callBehaviorModel(List.of(newTransaction));

        // Aggregate the scores
        return aggregateScores(riskScore, behaviorScore);
    }

    private double callRiskModel(List<Operation> transactions) {
        // Example payload creation (serialize transactions as JSON)
        // Adjust as per API specs
        return restTemplate.postForObject(RISK_MODEL_API_URL, transactions, Double.class);
    }

    private double callBehaviorModel(List<Operation> transactions) {
        // Example payload creation (serialize transactions as JSON)
        // Adjust as per API specs
        return restTemplate.postForObject(BEHAVIOR_MODEL_API_URL, transactions, Double.class);
    }

    private double aggregateScores(double riskScore, double behaviorScore) {
        // Simple weighted average (adjust as needed)
        return (riskScore * 0.6) + (behaviorScore * 0.4);
    }
}
