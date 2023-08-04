package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    
    //findByPayerIdOrderByRequestIdDesc
    List<Transaction> findByPayerIdOrderByIdDesc(int payerId);

    //get all desc
    List<Transaction> findAllByOrderByIdDesc();
}
