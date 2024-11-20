package com.komillog.komibank.service;

import com.komillog.komibank.model.Operation;
import com.komillog.komibank.model.User;

/**
 * Interface for the ModelBroker service.
 * It defines the methods to calculate risk scores and handle new transactions.
 */
public interface ModelBroker {

    /**
     * Reads the last 1000 transactions and user behavior data, calls the APIs,
     * and aggregates the risk scores.
     *
     * @return Aggregated risk score.
     */
    double calculateAggregatedRisk(Long account);

    /**
     * Processes a new transaction and associated user behavior data.
     *
     * @param newTransaction The new transaction.
     * @param user           The user associated with the transaction.
     * @return Aggregated risk score including the new data.
     */
    double processNewTransaction(Operation newTransaction, User user);
}