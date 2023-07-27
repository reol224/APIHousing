package com.conestoga.APIHousing.model;

import org.springframework.data.annotation.Id;

import com.conestoga.APIHousing.dtos.SubresidenceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "subresidence")
public class Subresidence {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
     @JsonIgnore  // Add this annotation to break the cycle in JSON serialization

    private Residence residence;

    @Column(name = "unit_type")
    private String unitType;

    @Column(name = "unit_description")
    private String unitDescription;

    @Column(name = "monthly_rent")
    private BigDecimal monthlyRent;


    @Column(name = "img")
    private String img;


    public Long getunit_id() {
        return unit_id;
    }

    public void setunit_id(Long unit_id) {
        this.unit_id = unit_id;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
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
