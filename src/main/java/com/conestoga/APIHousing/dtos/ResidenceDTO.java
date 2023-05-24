package com.conestoga.APIHousing.dtos;

public class ResidenceDTO {

    private Long residenceId;
    private String name;
    private String address;
    private String description;
    private Long managerId;

    public ResidenceDTO(Long residenceId, String name, String address, String description, Long managerId) {
        this.residenceId = residenceId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.managerId = managerId;
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
}

