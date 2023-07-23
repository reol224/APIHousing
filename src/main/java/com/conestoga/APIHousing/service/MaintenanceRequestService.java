package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.MaintenanceRequestDTO;
import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.MaintenanceRequestRepository;
import com.conestoga.APIHousing.interfaces.UnitRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.MaintenanceRequest;
import com.conestoga.APIHousing.model.Unit;
import com.conestoga.APIHousing.utils.FileUpload;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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

    public MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestDTO maintenanceRequestDTO) throws IOException {
        MaintenanceRequest maintenanceRequest = maintenanceRequestDTO.toModel();
        Unit unit = unitRepository.findById(maintenanceRequestDTO.getUnitId()).orElse(null);
        Account user = accountRepository.findById(maintenanceRequestDTO.getUserId()).orElse(null);
        if (unit != null && user != null) {
            maintenanceRequest.setUnit(unit);
            maintenanceRequest.setUser(user);

            //if dto's date is null, set current date
            if (maintenanceRequestDTO.getRequestDate() == null) {
                maintenanceRequest.setRequestDate(new java.util.Date());
            }
            
            
            //if image is not null, upload image
            if (maintenanceRequestDTO.getImg() != null) {
               maintenanceRequest.setImg(FileUpload.convertBase64ToFile( (maintenanceRequestDTO.getImg())));
            }
            MaintenanceRequest createdMaintenanceRequest = maintenanceRequestRepository.save(maintenanceRequest);
            return   MaintenanceRequestDTO.fromModel(createdMaintenanceRequest);
        }
        return null;
    }


    public MaintenanceRequestDTO getMaintenanceRequestById(Long requestId) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(requestId).orElse(null);
        if (maintenanceRequest != null) {
            return MaintenanceRequestDTO.fromModel(maintenanceRequest);
        }
        return null;
    }

    public MaintenanceRequestDTO updateMaintenanceRequest(Long requestId, MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequest existingMaintenanceRequest = maintenanceRequestRepository.findById(requestId).orElse(null);
        if (existingMaintenanceRequest != null) {
            MaintenanceRequest updatedMaintenanceRequest = maintenanceRequestDTO.toModel();
            MaintenanceRequest savedMaintenanceRequest = maintenanceRequestRepository.save(updatedMaintenanceRequest);
            return MaintenanceRequestDTO.fromModel(savedMaintenanceRequest);
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
        List<MaintenanceRequestDTO> maintenanceRequestDTOS = new ArrayList<MaintenanceRequestDTO>();
        for (MaintenanceRequest maintenanceRequest : maintenanceRequests) {
            maintenanceRequestDTOS.add(MaintenanceRequestDTO.fromModel(maintenanceRequest));
        }
        return maintenanceRequestDTOS;

    
    }

  

    public List<MaintenanceRequestDTO> getAllMaintenanceRequestsByUserId(Long userId) {
 
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findByUserIdOrderByRequestIdDesc(userId);
           List<MaintenanceRequestDTO> maintenanceRequestDTOS = new ArrayList<MaintenanceRequestDTO>();
        for (MaintenanceRequest maintenanceRequest : maintenanceRequests) {
            maintenanceRequestDTOS.add(MaintenanceRequestDTO.fromModel(maintenanceRequest));
        }
        return maintenanceRequestDTOS;

    }
}

