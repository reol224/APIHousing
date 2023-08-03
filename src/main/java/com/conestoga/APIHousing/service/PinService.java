package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.model.Pin;
import com.conestoga.APIHousing.interfaces.PinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PinService {
    Logger logger = Logger.getLogger(PinService.class.getName());
    private final PinRepository pinRepository;

    @Autowired
    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public Pin savePin(Pin pin) {
        logger.info("Pin saved for user: " + pin.getEmail());
        return pinRepository.save(pin);
    }

    public boolean checkPinByEmailAndPinCode(String email, String pinCode) {
        Pin pin = pinRepository.findPinByEmail(email, pinCode);
        if (email == null || pinCode == null) {
            logger.warning("Email or PinCode is null");
            return false;
        } else {
            logger.info("Pin found for user: " + email);
            return pin.getPinCode().equals(pinCode);
        }
    }
}
