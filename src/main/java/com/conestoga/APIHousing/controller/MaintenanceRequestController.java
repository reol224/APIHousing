package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.MaintenanceRequestDTO;
import com.conestoga.APIHousing.service.MaintenanceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-requests")
public class MaintenanceRequestController {

    private final MaintenanceRequestService maintenanceRequestService;

    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;
    }

    @PostMapping
    public ResponseEntity<MaintenanceRequestDTO> createMaintenanceRequest(
            @RequestBody MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequestDTO createdMaintenanceRequest = maintenanceRequestService
                .createMaintenanceRequest(maintenanceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaintenanceRequest);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<MaintenanceRequestDTO> getMaintenanceRequestById(@PathVariable Long requestId) {
        MaintenanceRequestDTO maintenanceRequestDTO = maintenanceRequestService.getMaintenanceRequestById(requestId);
        if (maintenanceRequestDTO != null) {
            return ResponseEntity.ok(maintenanceRequestDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<MaintenanceRequestDTO> updateMaintenanceRequest(@PathVariable Long requestId,
            @RequestBody MaintenanceRequestDTO maintenanceRequestDTO) {
        MaintenanceRequestDTO updatedMaintenanceRequest = maintenanceRequestService.updateMaintenanceRequest(requestId,
                maintenanceRequestDTO);
        if (updatedMaintenanceRequest != null) {
            return ResponseEntity.ok(updatedMaintenanceRequest);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteMaintenanceRequest(@PathVariable Long requestId) {
        boolean deleted = maintenanceRequestService.deleteMaintenanceRequest(requestId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceRequestDTO>> getAllMaintenanceRequests() {
        List<MaintenanceRequestDTO> maintenanceRequests = maintenanceRequestService.getAllMaintenanceRequests();
        return ResponseEntity.ok(maintenanceRequests);
    }
}
