package com.conestoga.APIHousing.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conestoga.APIHousing.model.RoommateProfile;

@Repository
public interface RoommateProfileRepository extends JpaRepository<RoommateProfile, Long> {
    // Add any custom queries or methods if needed
        Optional<RoommateProfile> findByUserId(Long userId);

}