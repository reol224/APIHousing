package com.conestoga.APIHousing.interfaces.Booking;

import com.conestoga.APIHousing.model.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByItemId(Long itemId);
    
        boolean existsByItemId(Long itemId);

        List<Booking> findByUserId(Long userId);

    // Add more custom queries as per requirements
}