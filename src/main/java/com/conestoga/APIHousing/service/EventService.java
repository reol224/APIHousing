package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.EventRepository;
import com.conestoga.APIHousing.interfaces.RsvpRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.Event;
import com.conestoga.APIHousing.model.Rsvp;
import com.conestoga.APIHousing.utils.FileUpload;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EventService {
    private final EventRepository eventRepository;
    private final RsvpRepository rsvpRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public EventService(EventRepository eventRepository, RsvpRepository rsvpRepository, AccountRepository accountRepository) {
        this.eventRepository = eventRepository;
        this.rsvpRepository = rsvpRepository;
        this.accountRepository = accountRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByIdDesc();
    }

    public List<Event> getActiveEvents() {
        return eventRepository.findActiveEventsOrderByIdDesc();
    }

    public Event getEventById(Long eventId) throws Exception {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            throw new Exception("Event not found with ID: " + eventId);
        }
        return optionalEvent.get();
    }

    @Transactional
    public boolean rsvpForEvent(Long eventId, Long userId) throws Exception  {
        Event event = getEventById(eventId);
        Account user  = accountRepository.findById(userId).orElseThrow();
        Rsvp rsvp = new Rsvp(user, event);
        rsvpRepository.save(rsvp);
        return true;
    }

  @Transactional
public boolean cancelRsvpForEvent(Long eventId, Long userId) throws Exception {
    Optional<Rsvp> optionalRsvp = rsvpRepository.findByEventIdAndUserId(eventId, userId);
    if (optionalRsvp.isPresent()) {
        Rsvp rsvp = optionalRsvp.get();
        rsvpRepository.delete(rsvp);
        return true;
    } else {
        throw new Exception("RSVP not found for Event ID: " + eventId + " and User ID: " + userId);
    }
}

  public Event createEvent(Event event) throws Exception {
        try {
            event.setEventBanner(FileUpload.convertBase64ToFile(event.getEventBanner()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
                    throw new Exception("Failed to save image");

        }
        return eventRepository.save(event);
    }

  public boolean hasRsvp(Long eventId, Long userId) {
        Optional<Rsvp> optionalRsvp = rsvpRepository.findByEventIdAndUserId(eventId, userId);
        return optionalRsvp.isPresent();
    }

public boolean deleteEvent(Long id) {
    Optional<Event> optionalEvent = eventRepository.findById(id);
    if (optionalEvent.isPresent()) {
        Event event = optionalEvent.get();
        eventRepository.delete(event);
        return true;
    } else {
        return false;
    }
}

}
