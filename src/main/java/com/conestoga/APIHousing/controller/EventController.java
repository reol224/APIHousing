package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.model.Event;
import com.conestoga.APIHousing.service.EventService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @PostMapping("/create")
public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    //try and catch error
    try {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
 //deletemapping for event
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
    boolean success = eventService.deleteEvent(id);
    if (success) {
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Failed to delete event", HttpStatus.BAD_REQUEST);
    }
}



    @GetMapping("/active")
    public ResponseEntity<List<Event>> getActiveEvents() {
        List<Event> activeEvents = eventService.getActiveEvents();
        return new ResponseEntity<>(activeEvents, HttpStatus.OK);
    }

  @PostMapping("/rsvp")
public ResponseEntity<String> rsvpForEvent( @RequestBody Map<String, Long> requestBody) throws Exception {
    Long userId = requestBody.get("user_id");
        Long eventId = requestBody.get("event_id");

    boolean success = eventService.rsvpForEvent(eventId, userId);
    if (success) {
        return new ResponseEntity<>( HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Failed to RSVP for event", HttpStatus.BAD_REQUEST);
    }
}

@GetMapping("/hasRsvp")
public ResponseEntity<Boolean> hasRsvp(@RequestParam Long event_id, @RequestParam Long user_id) {
    boolean hasRsvp = eventService.hasRsvp(event_id, user_id);
    // return in json {"rsvp": true/false}
    return new ResponseEntity<>(hasRsvp, HttpStatus.OK);
}

@DeleteMapping("/rsvp")
public ResponseEntity<String> cancelRsvpForEvent(@RequestBody Map<String, Long> requestBody) throws Exception {
    Long userId = requestBody.get("user_id");
    Long eventId = requestBody.get("event_id");

    boolean success = eventService.cancelRsvpForEvent(eventId, userId);
    if (success) {
        return new ResponseEntity<>( HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Failed to cancel RSVP for event", HttpStatus.BAD_REQUEST);
    }
}

}
