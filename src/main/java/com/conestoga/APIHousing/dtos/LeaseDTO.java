package com.conestoga.APIHousing.dtos;

import java.time.LocalDate;

public class LeaseDTO {
    private Long leaseId;
    private Long unitId;
    private Long userId;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
    private Integer leaseLength;
    private String leaseStatus;

    public LeaseDTO(Long unitId, Long userId, LocalDate leaseStartDate, LocalDate leaseEndDate, Integer leaseLength, String leaseStatus) {
        this.unitId = unitId;
        this.userId = userId;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.leaseLength = leaseLength;
        this.leaseStatus = leaseStatus;
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

    public LocalDate getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(LocalDate leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public LocalDate getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(LocalDate leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public Integer getLeaseLength() {
        return leaseLength;
    }

    public void setLeaseLength(Integer leaseLength) {
        this.leaseLength = leaseLength;
    }

    public String getLeaseStatus() {
        return leaseStatus;
    }

    public void setLeaseStatus(String leaseStatus) {
        this.leaseStatus = leaseStatus;
    }
}
