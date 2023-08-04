package com.conestoga.APIHousing.interfaces;

import com.conestoga.APIHousing.model.Rsvp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface RsvpRepository extends JpaRepository<Rsvp, Long> {
        Optional<Rsvp> findByEventIdAndUserId(Long eventId, Long userId);

        //count rsvp by event id
        Long countByEventId(Long eventId);
}
