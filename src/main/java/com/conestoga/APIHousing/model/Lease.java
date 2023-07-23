package com.conestoga.APIHousing.model;

import org.springframework.data.annotation.Id;

import com.google.type.DateTime;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "leases")
public class Lease {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lease_id")
    private Long leaseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account user;

    @Column(name = "lease_start_date")
    private Date leaseStartDate;

    @Column(name = "lease_end_date")
    private Date leaseEndDate;

    @Column(name = "lease_length")
    private Integer leaseLength;

    @Column(name = "lease_status")
    private int leaseStatus;

 
    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public Date getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Date leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Integer getLeaseLength() {
        return leaseLength;
    }

    public void setLeaseLength(Integer leaseLength) {
        this.leaseLength = leaseLength;
    }

    public int getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(int leaseStatus) {
        this.leaseStatus = leaseStatus;
    }
}
