package com.conestoga.APIHousing.interfaces.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.conestoga.APIHousing.model.booking.BookableObject;

@Repository
public interface BookableObjectRepository extends JpaRepository<BookableObject, Long> {
    // Custom queries, if needed
}
