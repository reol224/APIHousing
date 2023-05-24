package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.UnitDTO;
import com.conestoga.APIHousing.interfaces.ResidenceRepository;
import com.conestoga.APIHousing.interfaces.UnitRepository;
import com.conestoga.APIHousing.model.Unit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final ResidenceRepository residenceRepository;

    public UnitService(UnitRepository unitRepository, ResidenceRepository residenceRepository) {
        this.unitRepository = unitRepository;
        this.residenceRepository = residenceRepository;
    }

    public UnitDTO createUnit(UnitDTO unitDTO) {
        Unit unit = new Unit();
        unit.setResidence(residenceRepository.findById(unitDTO.getResidenceId()).orElse(null));
        unit.setUnitNumber(unitDTO.getUnitNumber());
        unit.setUnitType(unitDTO.getUnitType());
        unit.setUnitDescription(unitDTO.getUnitDescription());
        unit.setMonthlyRent(unitDTO.getMonthlyRent());

        Unit savedUnit = unitRepository.save(unit);

        return convertToDTO(savedUnit);
    }

    public UnitDTO getUnitById(Long unitId) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        return unitOptional.map(this::convertToDTO).orElse(null);
    }

    public UnitDTO updateUnit(Long unitId, UnitDTO unitDTO) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
            Unit unit = unitOptional.get();
            unit.setResidence(residenceRepository.findById(unitDTO.getResidenceId()).orElse(null));
            unit.setUnitNumber(unitDTO.getUnitNumber());
            unit.setUnitType(unitDTO.getUnitType());
            unit.setUnitDescription(unitDTO.getUnitDescription());
            unit.setMonthlyRent(unitDTO.getMonthlyRent());

            Unit updatedUnit = unitRepository.save(unit);

            return convertToDTO(updatedUnit);
        }
        return null;
    }

    public boolean deleteUnit(Long unitId) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
            unitRepository.delete(unitOptional.get());
            return true;
        }
        return false;
    }

    public List<UnitDTO> getAllUnits() {
        List<Unit> units = unitRepository.findAll();
        return units.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UnitDTO convertToDTO(Unit unit) {
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setUnitId(unit.getUnitId());
        unitDTO.setResidenceId(unit.getResidence().getResidenceId());
        unitDTO.setUnitNumber(unit.getUnitNumber());
        unitDTO.setUnitType(unit.getUnitType());
        unitDTO.setUnitDescription(unit.getUnitDescription());
        unitDTO.setMonthlyRent(unit.getMonthlyRent());
        return unitDTO;
    }
}


