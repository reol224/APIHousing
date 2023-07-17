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
@RequestMapping("/events")
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
    
    @PostMapping("/events")
public ResponseEntity<Event> createEvent(@RequestBody Event event) {
    Event createdEvent = eventService.createEvent(event);
    return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
}

    @GetMapping("/active")
    public ResponseEntity<List<Event>> getActiveEvents() {
        List<Event> activeEvents = eventService.getActiveEvents();
        return new ResponseEntity<>(activeEvents, HttpStatus.OK);
    }

  @PostMapping("/rsvp/{eventId}")
public ResponseEntity<String> rsvpForEvent(@PathVariable Long eventId, @RequestBody Map<String, Long> requestBody) throws Exception {
    Long userId = requestBody.get("user_id");
    boolean success = eventService.rsvpForEvent(eventId, userId);
    if (success) {
        return new ResponseEntity<>("RSVP successful", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Failed to RSVP for event", HttpStatus.BAD_REQUEST);
    }
}

@DeleteMapping("/rsvp/{eventId}")
public ResponseEntity<String> cancelRsvpForEvent(@PathVariable Long eventId, @RequestBody Map<String, Long> requestBody) throws Exception {
    Long userId = requestBody.get("user_id");
    boolean success = eventService.cancelRsvpForEvent(eventId, userId);
    if (success) {
        return new ResponseEntity<>("RSVP canceled", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Failed to cancel RSVP for event", HttpStatus.BAD_REQUEST);
    }
}

}
