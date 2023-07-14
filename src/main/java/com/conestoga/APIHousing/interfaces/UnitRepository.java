package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.Residence;
import com.conestoga.APIHousing.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByResidence(Residence residence);

    List<Unit> findByUnitType(String unitType);

    List<Unit> findByMonthlyRentLessThan(BigDecimal rent);
}
