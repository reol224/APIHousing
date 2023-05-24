package com.conestoga.APIHousing.dtos;

import java.math.BigDecimal;

public class UnitDTO {

    private Long unitId;
    private Long residenceId;
    private String unitNumber;
    private String unitType;
    private String unitDescription;
    private BigDecimal monthlyRent;

    public UnitDTO(Long unitId, Long residenceId, String unitNumber, String unitType, String unitDescription, BigDecimal monthlyRent) {
        this.unitId = unitId;
        this.residenceId = residenceId;
        this.unitNumber = unitNumber;
        this.unitType = unitType;
        this.unitDescription = unitDescription;
        this.monthlyRent = monthlyRent;
    }

    public UnitDTO() {

    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }
}

