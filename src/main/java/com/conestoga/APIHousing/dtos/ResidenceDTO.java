package com.conestoga.APIHousing.dtos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.conestoga.APIHousing.model.Residence;
import com.fasterxml.jackson.annotation.JsonInclude;

//impoer javautil.List;

//include not null fields

// @JsonInclude(JsonInclude.Include.ALWAYS)
public class ResidenceDTO {

    private Long residenceId;
    private String name;
    private String description;
    private String address;
    private Long managerId;
    private Optional<String> img;
    private List<UnitDTO> units;
    //dto from model
    public ResidenceDTO(Residence residence) {
        this.residenceId = residence.getResidenceId();
        this.name = residence.getName();
        this.address = residence.getAddress();
        this.description = residence.getDescription();
        this.managerId = residence.getManager().getId();
        this.img = Optional.ofNullable(residence.getImg());
           this.units = residence.getUnits().stream().map(UnitDTO::new).collect(Collectors.toList());
   
    }

    //from model to dto
    public Residence convertToResidence() {
        Residence residence = new Residence();
        residence.setResidenceId(this.residenceId);
        residence.setName(this.name);
        residence.setAddress(this.address);
        residence.setDescription(this.description);
        residence.setImg(this.img.get());
        return residence;
    }

    public ResidenceDTO() {
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public List<UnitDTO> getUnits() {
        return units;
    }

    public void setUnits(List<UnitDTO> units) {
        this.units = units;
    }

    //getter and setter for img
    public Optional<String> getImg() {
        return img;
    }

    public void setImg(Optional<String> img) {
        this.img = img;
    }
}

