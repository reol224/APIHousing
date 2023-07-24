package com.conestoga.APIHousing.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.conestoga.APIHousing.model.Transaction;

public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private Timestamp createdAt;
    private String description;
    private int paymentFor;

    public TransactionDTO() {
    }

    public TransactionDTO(Long id, BigDecimal amount, Timestamp createdAt, String description, int paymentFor) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.description = description;
        this.paymentFor = paymentFor;
    }

    //TransactionDTO from Transaction constructor
    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.createdAt = transaction.getCreatedAt();
        this.description = transaction.getDescription();
        this.paymentFor = transaction.getPaymentFor();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(int paymentFor) {
        this.paymentFor = paymentFor;
    }


}
