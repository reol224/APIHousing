package com.conestoga.APIHousing.dtos;

import java.math.BigDecimal;

import com.conestoga.APIHousing.model.Unit;

public class UnitDTO {

    private Long unitId;
    private Long residenceId;
    private String unitNumber;
    private String unitType;
    private String unitDescription;
    private BigDecimal monthlyRent;
    private String img;
    private String test;
        //dto from model

    //from model to dto
    public UnitDTO(Unit unit) {
        this.unitId = unit.getunit_id();
        this.residenceId = unit.getResidence().getResidenceId();
        this.unitNumber = unit.getUnitNumber();
        this.unitType = unit.getUnitType();
        this.unitDescription = unit.getUnitDescription();
        this.monthlyRent = unit.getMonthlyRent();
        this.test = "test";
        this.img = unit.getImg();
        
    }

    //to model
    public Unit convertToUnit() {
        Unit unit = new Unit();
        unit.setunit_id(this.unitId);
        unit.setUnitNumber(this.unitNumber);
        unit.setUnitType(this.unitType);
        unit.setUnitDescription(this.unitDescription);
        unit.setMonthlyRent(this.monthlyRent);
        unit.setImg(this.img);
        return unit;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

