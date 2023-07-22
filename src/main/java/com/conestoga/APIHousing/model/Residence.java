package com.conestoga.APIHousing.model;


import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.conestoga.APIHousing.dtos.ResidenceDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "residences")
public class Residence {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "residence_id")
    private Long residenceId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "img")
    private String img;


       // Add the @OneToMany mapping for units
    @OneToMany(mappedBy = "residence", fetch = FetchType.LAZY)
    private List<Unit> units;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore  // Add this annotation to break the cycle in JSON serialization
    private Account manager;


 
    // Getters and setters for the existing properties

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }
  
    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getManager() {
        return manager;
    }

    public void setManager(Account manager) {
        this.manager = manager;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
