package com.conestoga.APIHousing.interfaces;


import com.conestoga.APIHousing.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.eventStatus IN (1, 2) ORDER BY e.id DESC")
    List<Event> findActiveEventsOrderByIdDesc();

        List<Event> findAllByOrderByIdDesc();

}
