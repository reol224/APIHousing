package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.RoommateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoommateProfileRepository extends JpaRepository<RoommateProfile, Long> {
    // Add any custom queries or methods if needed
        Optional<RoommateProfile> findByUserId(Long userId);

}