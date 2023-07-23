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
        Residence residence = residenceDTO.convertToResidence();
        Account manager = accountRepository.findById(residenceDTO.getManagerId()).orElse(null);
        if (manager != null) {
            residence.setManager(manager);
            Residence createdResidence = residenceRepository.save(residence);
            return new ResidenceDTO(createdResidence);
        }
        return null;
    }

    public ResidenceDTO getResidenceById(Long residenceId) {
        Residence residence = residenceRepository.findById(residenceId).orElse(null);
        if (residence != null) {
                       return new ResidenceDTO(residence);
        }
        return null;
    }

    public ResidenceDTO updateResidence(Long residenceId, ResidenceDTO residenceDTO) {
        Residence existingResidence = residenceRepository.findById(residenceId).orElse(null);
        if (existingResidence != null) {
            Residence updatedResidence = existingResidence;
            updatedResidence.setManager(existingResidence.getManager());
            Residence savedResidence = residenceRepository.save(updatedResidence);
            return new ResidenceDTO(savedResidence);
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

        //stream and map and conver to ResidenceDTO by ResidenceDTO constructor
             return residences.stream().map(ResidenceDTO::new).collect(Collectors.toList());

        // return residences.stream()
        //         .map()
        //         .collect(Collectors.toList());
    }

  

  
}

