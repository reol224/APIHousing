package com.conestoga.APIHousing.interfaces;


import com.conestoga.APIHousing.model.Event;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.eventStatus IN (1, 2)")
    List<Event> findActiveEvents();

}
