package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.LeaseDTO;
import com.conestoga.APIHousing.service.LeaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leases")
public class LeaseController {

    private final LeaseService leaseService;

    public LeaseController(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<LeaseDTO> createLease(@RequestBody LeaseDTO leaseDTO) {
        try {
            LeaseDTO createdLease = leaseService.createLease(leaseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLease);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{leaseId}")
    public ResponseEntity<LeaseDTO> getLeaseById(@PathVariable Long leaseId) {
        LeaseDTO leaseDTO = leaseService.getLeaseById(leaseId);
        if (leaseDTO != null) {
            return ResponseEntity.ok(leaseDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{leaseId}")
    public ResponseEntity<LeaseDTO> updateLease(@PathVariable Long leaseId, @RequestBody LeaseDTO leaseDTO) {
        try {
            LeaseDTO updatedLease = leaseService.updateLease(leaseId, leaseDTO);
            if (updatedLease != null) {
                return ResponseEntity.ok(updatedLease);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{leaseId}")
    public ResponseEntity<Void> deleteLease(@PathVariable Long leaseId) {
        boolean deleted = leaseService.deleteLease(leaseId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaseDTO>> getAllLeases() {
        List<LeaseDTO> leases = leaseService.getAllLeases();
        return ResponseEntity.ok(leases);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        LeaseDTO leaseDTO = new LeaseDTO(Long.parseLong("1"), Long.parseLong("1"), LocalDate.now(), LocalDate.of(2025, Month.APRIL, 12), 50, "Active");
        try {
            leaseService.createLease(leaseDTO);
            return ResponseEntity.ok("Test");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
