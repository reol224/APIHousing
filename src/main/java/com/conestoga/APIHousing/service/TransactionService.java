package com.conestoga.APIHousing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conestoga.APIHousing.dtos.TransactionDTO;
import com.conestoga.APIHousing.interfaces.TransactionRepository;
import com.conestoga.APIHousing.model.Transaction;

import java.util.List;

import java.util.stream.Collectors;



@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> getTransactionsByPayerId(String payerId) {
        List<Transaction> transactions = transactionRepository.findByPayerId(payerId);
        return transactions.stream()
                .map(TransactionDTO::new)
                .collect(Collectors.toList());
    }

    public Transaction createTransaction(Transaction transaction) {
        Transaction t = transactionRepository.save(transaction);
         System.out.println("Transaction saved");
        return t;
    }
}

