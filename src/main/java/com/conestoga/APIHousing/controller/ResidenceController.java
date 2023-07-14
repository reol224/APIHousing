package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.ResidenceDTO;
import com.conestoga.APIHousing.service.ResidenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residences")
public class ResidenceController {

    private final ResidenceService residenceService;

    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @PostMapping
    public ResponseEntity<ResidenceDTO> createResidence(@RequestBody ResidenceDTO residenceDTO) {
        ResidenceDTO createdResidence = residenceService.createResidence(residenceDTO);
        if (createdResidence != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResidence);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{residenceId}")
    public ResponseEntity<ResidenceDTO> getResidenceById(@PathVariable Long residenceId) {
        ResidenceDTO residenceDTO = residenceService.getResidenceById(residenceId);
        if (residenceDTO != null) {
            return ResponseEntity.ok(residenceDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{residenceId}")
    public ResponseEntity<ResidenceDTO> updateResidence(@PathVariable Long residenceId,
                                                        @RequestBody ResidenceDTO residenceDTO) {
        ResidenceDTO updatedResidence = residenceService.updateResidence(residenceId, residenceDTO);
        if (updatedResidence != null) {
            return ResponseEntity.ok(updatedResidence);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{residenceId}")
    public ResponseEntity<Void> deleteResidence(@PathVariable Long residenceId) {
        boolean deleted = residenceService.deleteResidence(residenceId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResidenceDTO>> getAllResidences() {
        List<ResidenceDTO> residences = residenceService.getAllResidences();
        return ResponseEntity.ok(residences);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        ResidenceDTO residenceDTO = new ResidenceDTO(Long.parseLong("1"), "bloc prapadit", "waterloo", "descriere",
                Long.parseLong("1"));
        residenceService.createResidence(residenceDTO);
        return ResponseEntity.ok("Test");
    }
}
