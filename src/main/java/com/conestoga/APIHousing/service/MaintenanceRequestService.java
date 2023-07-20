package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.MaintenanceRequestDTO;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.MaintenanceRequestRepository;
import com.conestoga.APIHousing.interfaces.UnitRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.MaintenanceRequest;
import com.conestoga.APIHousing.model.Unit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceRequestService {

    private final MaintenanceRequestRepository maintenanceRequestRepository;
    private final UnitRepository unitRepository;
    private final AccountRepository accountRepository;

    public MaintenanceRequestService(MaintenanceRequestRepository maintenanceRequestRepository,
                                     UnitRepository unitRepository,
                                     AccountRepository accountRepository) {
        this.maintenanceRequestRepository = maintenanceRequestRepository;
        this.unitRepository = unitRepository;
        this.accountRepository = accountRepository;
    }

    public MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequest maintenanceRequest = convertToMaintenanceRequest(maintenanceRequestDTO);
        Unit unit = unitRepository.findById(maintenanceRequestDTO.getUnitId()).orElse(null);
        Account user = accountRepository.findById(maintenanceRequestDTO.getUserId()).orElse(null);
        if (unit != null && user != null) {
            maintenanceRequest.setUnit(unit);
            maintenanceRequest.setUser(user);
            MaintenanceRequest createdMaintenanceRequest = maintenanceRequestRepository.save(maintenanceRequest);
            return convertToMaintenanceRequestDTO(createdMaintenanceRequest);
        }
        return null;
    }

    public MaintenanceRequestDTO getMaintenanceRequestById(Long requestId) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(requestId).orElse(null);
        if (maintenanceRequest != null) {
            return convertToMaintenanceRequestDTO(maintenanceRequest);
        }
        return null;
    }

    public MaintenanceRequestDTO updateMaintenanceRequest(Long requestId, MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequest existingMaintenanceRequest = maintenanceRequestRepository.findById(requestId).orElse(null);
        if (existingMaintenanceRequest != null) {
            MaintenanceRequest updatedMaintenanceRequest = convertToMaintenanceRequest(maintenanceRequestDTO);
            updatedMaintenanceRequest.setId(existingMaintenanceRequest.getId());
            updatedMaintenanceRequest.setUnit(existingMaintenanceRequest.getUnit());
            updatedMaintenanceRequest.setUser(existingMaintenanceRequest.getUser());
            MaintenanceRequest savedMaintenanceRequest = maintenanceRequestRepository.save(updatedMaintenanceRequest);
            return convertToMaintenanceRequestDTO(savedMaintenanceRequest);
        }
        return null;
    }

    public boolean deleteMaintenanceRequest(Long requestId) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(requestId).orElse(null);
        if (maintenanceRequest != null) {
            maintenanceRequestRepository.delete(maintenanceRequest);
            return true;
        }
        return false;
    }

    public List<MaintenanceRequestDTO> getAllMaintenanceRequests() {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findAll();
        return maintenanceRequests.stream()
                .map(this::convertToMaintenanceRequestDTO)
                .collect(Collectors.toList());
    }

    private MaintenanceRequest convertToMaintenanceRequest(MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setRequestDate(maintenanceRequestDTO.getRequestDate());
        maintenanceRequest.setRequestDescription(maintenanceRequestDTO.getRequestDescription());
        maintenanceRequest.setRequestStatus(maintenanceRequestDTO.getRequestStatus());
        maintenanceRequest.setImg(maintenanceRequestDTO.getImg());
        maintenanceRequest.setRemarks(maintenanceRequestDTO.getRemarks());

        return maintenanceRequest;
    }

    private MaintenanceRequestDTO convertToMaintenanceRequestDTO(MaintenanceRequest maintenanceRequest) {
        MaintenanceRequestDTO maintenanceRequestDTO = new MaintenanceRequestDTO();
        maintenanceRequestDTO.setRequestId(maintenanceRequest.getRequestId());
        maintenanceRequestDTO.setUnitId(maintenanceRequest.getUnit().getUnitId());
        maintenanceRequestDTO.setUserId(maintenanceRequest.getUser().getUserId());
        maintenanceRequestDTO.setRequestDate(maintenanceRequest.getRequestDate());
        maintenanceRequestDTO.setRequestDescription(maintenanceRequest.getRequestDescription());
        maintenanceRequestDTO.setRequestStatus(maintenanceRequest.getRequestStatus());
        maintenanceRequestDTO.setImg(maintenanceRequest.getImg());
        maintenanceRequestDTO.setRemarks(maintenanceRequest.getRemarks());
        return maintenanceRequestDTO;
    }
}

