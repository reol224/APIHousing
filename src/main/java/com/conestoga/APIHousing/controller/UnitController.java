package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.model.Unit;
import com.conestoga.APIHousing.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) throws IOException {
        Unit createdUnit = unitService.createUnit(unit);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUnit);
    }

    @GetMapping("/{unitId}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long unitId) {
        Unit unit = unitService.getUnitById(unitId);
        if (unit != null) {
            return ResponseEntity.ok(unit);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{unitId}")
    public ResponseEntity<Unit> updateUnit(@PathVariable Long unitId, @RequestBody Unit unit) throws IOException {
        Unit updatedUnit = unitService.updateUnit(unitId, unit);
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
    public ResponseEntity<List<Unit>> getAllUnits() {
        List<Unit> units = unitService.getAllUnits();
        return ResponseEntity.ok(units);
    }

}
