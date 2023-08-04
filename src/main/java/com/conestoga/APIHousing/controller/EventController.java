package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.model.Event;
import com.conestoga.APIHousing.model.Notification;
import com.conestoga.APIHousing.model.Rsvp;
import com.conestoga.APIHousing.service.EventService;
import com.conestoga.APIHousing.service.NotificationService;
import com.conestoga.APIHousing.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final NotificationService notificationService;

    @Autowired
    public EventController(EventService eventService, NotificationService notificationService) {
        this.eventService = eventService;
        this.notificationService = notificationService;
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
//putmapping for event
@PutMapping("/update")
public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
    try {
        Event updatedEvent = eventService.createEvent(event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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

    Rsvp resp = eventService.rsvpForEvent(eventId, userId);
    
     notificationService.create(new Notification("You RSVPed for "+resp.getEvent().getEventName(), userId, Constants.NOTIFICATION_TYPE_EVENT));

        return new ResponseEntity<>( HttpStatus.OK);
    
}

@GetMapping("/hasRsvp")
public ResponseEntity<Boolean> hasRsvp(@RequestParam Long event_id, @RequestParam Long user_id) {
    boolean hasRsvp = eventService.hasRsvp(event_id, user_id);
    // return in json {"rsvp": true/false}
    return new ResponseEntity<>(hasRsvp, HttpStatus.OK);
}
//get count of rsvp for event id
@GetMapping("/rsvpCount/{id}")
public ResponseEntity<Long> getRsvpCount(@PathVariable Long id) {
    Long rsvpCount = eventService.getRsvpCount(id);
    // return in json {"rsvpCount": 5}
    return new ResponseEntity<>(rsvpCount, HttpStatus.OK);
}

@DeleteMapping("/rsvp")
public ResponseEntity<String> cancelRsvpForEvent(@RequestBody Map<String, Long> requestBody) throws Exception {
    Long userId = requestBody.get("user_id");
    Long eventId = requestBody.get("event_id");

    Rsvp response = eventService.cancelRsvpForEvent(eventId, userId);
    notificationService.create(new Notification("You cancelled your RSVP for "+response.getEvent().getEventName(), userId, Constants.NOTIFICATION_TYPE_EVENT));
        return new ResponseEntity<>( HttpStatus.OK);
}
   

}
