package com.conestoga.APIHousing.dtos;

import java.util.Date;

import com.conestoga.APIHousing.model.Lease;

public class LeaseDTO {
    private Long leaseId;
    private Long unitId;
    private Long userId;
    private Date leaseStartDate;
    private Date leaseEndDate;
    private Integer leaseLength;
    private int leaseStatus;

    public LeaseDTO(Long unitId, Long userId, Date leaseStartDate, Date leaseEndDate, Integer leaseLength, int leaseStatus) {
        this.unitId = unitId;
        this.userId = userId;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.leaseLength = leaseLength;
        this.leaseStatus = leaseStatus;
    }

    //to model
    public Lease toModel() {
        Lease lease = new Lease();
        lease.setLeaseId(this.leaseId);
        lease.setLeaseStartDate(this.leaseStartDate);
        lease.setLeaseEndDate(this.leaseEndDate);
        lease.setLeaseLength(this.leaseLength);
        lease.setLeaseStatus(this.leaseStatus);
        return lease;
    }

    public LeaseDTO() {

    }

    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
