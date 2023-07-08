package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.UnitDTO;
import com.conestoga.APIHousing.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnit(@RequestBody UnitDTO unitDTO) {
        UnitDTO createdUnit = unitService.createUnit(unitDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUnit);
    }

    @GetMapping("/{unitId}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable Long unitId) {
        UnitDTO unitDTO = unitService.getUnitById(unitId);
        if (unitDTO != null) {
            return ResponseEntity.ok(unitDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<UnitDTO> updateUnit(@PathVariable Long unitId, @RequestBody UnitDTO unitDTO) {
        UnitDTO updatedUnit = unitService.updateUnit(unitId, unitDTO);
        if (updatedUnit != null) {
            return ResponseEntity.ok(updatedUnit);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long unitId) {
        boolean deleted = unitService.deleteUnit(unitId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UnitDTO>> getAllUnits() {
        List<UnitDTO> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        UnitDTO unitDTO = new UnitDTO(Long.parseLong("1"), Long.parseLong("1"), "76", "flat", "2 camere", BigDecimal.valueOf(234212));
        unitService.createUnit(unitDTO);
        return ResponseEntity.ok("Test");
    }
}

