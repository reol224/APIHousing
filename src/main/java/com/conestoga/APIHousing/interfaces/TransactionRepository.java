package com.conestoga.APIHousing.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conestoga.APIHousing.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // Add a method to get transactions by payerId
    List<Transaction> findByPayerId(String payerId);
}
