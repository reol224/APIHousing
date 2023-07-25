package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.model.Pin;
import com.conestoga.APIHousing.interfaces.PinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PinService {

    private final PinRepository pinRepository;

    @Autowired
    public PinService(PinRepository pinRepository) {
        this.pinRepository = pinRepository;
    }

    public Pin savePin(Pin pin) {
        return pinRepository.save(pin);
    }

    public boolean checkPinByEmailAndPinCode(String email, String pinCode) {
        Pin pin = pinRepository.findPinByEmail(email, pinCode);
        return pin != null && pin.getPinCode().equals(pinCode);
    }
}
