package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.ResidenceDTO;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.ResidenceRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.Residence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResidenceService {

    private final ResidenceRepository residenceRepository;
    private final AccountRepository accountRepository;

    public ResidenceService(ResidenceRepository residenceRepository, AccountRepository accountRepository) {
        this.residenceRepository = residenceRepository;
        this.accountRepository = accountRepository;
    }

    public ResidenceDTO createResidence(ResidenceDTO residenceDTO) {
        Residence residence = convertToResidence(residenceDTO);
        Account manager = accountRepository.findById(residenceDTO.getManagerId()).orElse(null);
        if (manager != null) {
            residence.setManager(manager);
            Residence createdResidence = residenceRepository.save(residence);
            return convertToResidenceDTO(createdResidence);
        }
        return null;
    }

    public ResidenceDTO getResidenceById(Long residenceId) {
        Residence residence = residenceRepository.findById(residenceId).orElse(null);
        if (residence != null) {
            return convertToResidenceDTO(residence);
        }
        return null;
    }

    public ResidenceDTO updateResidence(Long residenceId, ResidenceDTO residenceDTO) {
        Residence existingResidence = residenceRepository.findById(residenceId).orElse(null);
        if (existingResidence != null) {
            Residence updatedResidence = convertToResidence(residenceDTO);
            updatedResidence.setId(existingResidence.getId());
            updatedResidence.setManager(existingResidence.getManager());
            Residence savedResidence = residenceRepository.save(updatedResidence);
            return convertToResidenceDTO(savedResidence);
        }
        return null;
    }

    public boolean deleteResidence(Long residenceId) {
        Residence residence = residenceRepository.findById(residenceId).orElse(null);
        if (residence != null) {
            residenceRepository.delete(residence);
            return true;
        }
        return false;
    }

    public List<ResidenceDTO> getAllResidences() {
        List<Residence> residences = residenceRepository.findAll();
        return residences.stream()
                .map(this::convertToResidenceDTO)
                .collect(Collectors.toList());
    }

    private Residence convertToResidence(ResidenceDTO residenceDTO) {
        Residence residence = new Residence();
        residence.setName(residenceDTO.getName());
        residence.setAddress(residenceDTO.getAddress());
        residence.setDescription(residenceDTO.getDescription());
        return residence;
    }

    private ResidenceDTO convertToResidenceDTO(Residence residence) {
        ResidenceDTO residenceDTO = new ResidenceDTO();
        residenceDTO.setResidenceId(residence.getResidenceId());
        residenceDTO.setName(residence.getName());
        residenceDTO.setAddress(residence.getAddress());
        residenceDTO.setDescription(residence.getDescription());
        residenceDTO.setManagerId(residence.getManager().getUserId());
        return residenceDTO;
    }
}

