package com.olivechip.restaurant_waitlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.repository.GuestRepository;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    // create a new guest
    public Guest createGuest(Guest guest) {
        if (guest.getName() == null || guest.getPartySize() == null || guest.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Guest must have name, party size, and phone number");
        }
        return guestRepository.save(guest);
    }

    // retrieve all guests
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }
  
    // retrieve guest by id
    public Guest getGuestById(Integer id) {
        return guestRepository.findById(id).orElse(null);
    }

    // retrieve guest by name
    public Guest getGuestByName(String name) {
        return guestRepository.findByName(name).orElse(null);
    }

    // update guest if guest exists by name
    public Guest updateGuestByName(String name, Guest guest) {
        Guest existingGuest = guestRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Guest not found with name: " + name));

        if (guest.getName() != null) {
            existingGuest.setName(guest.getName());
        }
        if (guest.getPartySize() != null) {
            existingGuest.setPartySize(guest.getPartySize());
        }
        if (guest.getPhoneNumber() != null) {
            existingGuest.setPhoneNumber(guest.getPhoneNumber());
        }

        return guestRepository.save(existingGuest);
    }

    // delete guest if guest exists by name
    public void deleteGuestByName(String name) {
        Guest existingGuest = guestRepository.findByName(name)
            .orElseThrow(() -> new IllegalArgumentException("Guest not found with name: " + name));
        guestRepository.delete(existingGuest);
    }
}