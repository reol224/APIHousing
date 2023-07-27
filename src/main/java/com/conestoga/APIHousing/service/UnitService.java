package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.dtos.SubresidenceDTO;
import com.conestoga.APIHousing.interfaces.ResidenceRepository;
import com.conestoga.APIHousing.interfaces.SubresidenceRepository;
import com.conestoga.APIHousing.model.Subresidence;
import com.conestoga.APIHousing.utils.FileUpload;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final SubresidenceRepository unitRepository;
    private final ResidenceRepository residenceRepository;

    public UnitService(SubresidenceRepository unitRepository, ResidenceRepository residenceRepository) {
        this.unitRepository = unitRepository;
        this.residenceRepository = residenceRepository;
    }

    public Subresidence createUnit(Subresidence unit) throws IOException {
    unit.setImg(FileUpload.convertBase64ToFile(unit.getImg()));
     return unitRepository.save(unit);

    }

    public Subresidence getUnitById(Long unitId) {
        Optional<Subresidence> unitOptional = unitRepository.findById(unitId);
        return unitOptional.orElse(null);
    }

    public Subresidence updateUnit(Long unitId, Subresidence unit) throws IOException {
        Optional<Subresidence> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
          
                unit.setImg(FileUpload.convertBase64ToFile(unit.getImg()));


            // return convertToDTO(updatedUnit);

            return unit;
        }
        return null;
    }

    public boolean deleteUnit(Long unitId) {
        Optional<Subresidence> unitOptional = unitRepository.findById(unitId);
        if (unitOptional.isPresent()) {
            unitRepository.delete(unitOptional.get());
            return true;
        }
        return false;
    }

    public List<Subresidence> getAllUnits() {
        List<Subresidence> units = unitRepository.findAll();
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


