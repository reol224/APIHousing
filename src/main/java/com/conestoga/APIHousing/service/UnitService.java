package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.UnitDTO;
import com.conestoga.APIHousing.interfaces.ResidenceRepository;
import com.conestoga.APIHousing.interfaces.UnitRepository;
import com.conestoga.APIHousing.model.Unit;
import com.conestoga.APIHousing.utils.FileUpload;

import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public Unit createUnit(Unit unit) throws IOException {
    unit.setImg(FileUpload.convertBase64ToFile(unit.getImg()));
     return unitRepository.save(unit);

    }

    public Unit getUnitById(Long unitId) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        return unitOptional.orElse(null);
    }

    public Unit updateUnit(Long unitId, Unit unit) throws IOException {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
          
                unit.setImg(FileUpload.convertBase64ToFile(unit.getImg()));


            // return convertToDTO(updatedUnit);

            return unit;
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

    public List<Unit> getAllUnits() {
        List<Unit> units = unitRepository.findAll();
        // return units.stream()
        //         .map(this::convertToDTO)
        //         .collect(Collectors.toList());

        return units;
    }

    // private Unit convertToDTO(Unit unit) {
    //     Unit unitDTO = new UnitDTO();
    //     unitDTO.setUnitId(unit.getunit_id());
    //     unitDTO.setResidenceId(unit.getResidence().getResidenceId());
    //     unitDTO.setUnitNumber(unit.getUnitNumber());
    //     unitDTO.setUnitType(unit.getUnitType());
    //     unitDTO.setUnitDescription(unit.getUnitDescription());
    //     unitDTO.setMonthlyRent(unit.getMonthlyRent());
    //     return unitDTO;
    // }
}


