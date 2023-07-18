package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.interfaces.AccountRepository;
import com.conestoga.APIHousing.interfaces.EventRepository;
import com.conestoga.APIHousing.interfaces.RsvpRepository;
import com.conestoga.APIHousing.model.Account;
import com.conestoga.APIHousing.model.Event;
import com.conestoga.APIHousing.model.Rsvp;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
class RsvpService {

    private final RsvpRepository rsvpRepository;
    private final EventRepository eventRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public RsvpService(RsvpRepository rsvpRepository, EventRepository eventRepository, AccountRepository accountRepository) {
        this.rsvpRepository = rsvpRepository;
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
    }

    public boolean rsvpForEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow();

        Account user = accountRepository.findById(userId)
                .orElseThrow();

        Rsvp rsvp = new Rsvp(user, event);
        rsvpRepository.save(rsvp);
        return true;
    }

    public boolean cancelRsvpForEvent(Long eventId, Long userId) {
        Rsvp rsvp = rsvpRepository.findByEventIdAndUserId(eventId, userId)
                .orElseThrow();

        rsvpRepository.delete(rsvp);
        return true;
    }

    public Optional<Rsvp> findByEventIdAndUserId(Long eventId, Long userId) {
        return rsvpRepository.findByEventIdAndUserId(eventId, userId);
    }
}




