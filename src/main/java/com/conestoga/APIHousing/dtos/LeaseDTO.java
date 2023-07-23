package com.conestoga.APIHousing.dtos;

import java.util.Date;

import com.conestoga.APIHousing.model.Lease;
import com.conestoga.APIHousing.model.Unit;
import com.fasterxml.jackson.annotation.JsonFormat;

public class LeaseDTO {
   private Long leaseId;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaseStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaseEndDate;
    private Integer leaseLength;
    private int leaseStatus;
        private UnitDTO unit;



    //to model
    public Lease toModel() {
        Lease lease = new Lease();
        lease.setLeaseId(this.leaseId);
        lease.setLeaseStartDate(this.leaseStartDate);
        lease.setLeaseEndDate(this.leaseEndDate);
        lease.setLeaseLength(this.leaseLength);
        lease.setLeaseStatus(this.leaseStatus);
        lease.setUnit(this.unit.convertToUnit());
        return lease;
    }

    //fromDtro to model
    public  LeaseDTO (Lease lease) {
        this.setLeaseId(lease.getLeaseId());
        this.setLeaseStartDate(lease.getLeaseStartDate());
        this.setLeaseEndDate(lease.getLeaseEndDate());
        this.setLeaseLength(lease.getLeaseLength());
        this.setLeaseStatus(lease.getLeaseStatus());
        this.setunit(new UnitDTO(lease.getUnit()));
    }

    public LeaseDTO() {

    }

    public Long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Long leaseId) {
        this.leaseId = leaseId;
    }

    public UnitDTO getunit() {
        return unit;
    }

    public void setunit(UnitDTO unit) {
        this.unit = unit;
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
