package com.olivechip.restaurant_waitlist.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.WaitlistStatus;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.repository.WaitlistEntryRepository;

import jakarta.transaction.Transactional;

@Service
public class WaitlistEntryService {

    @Autowired
    private GuestService guestService;

    @Autowired
    private WaitlistEntryRepository waitlistEntryRepository;

    // create a new guest and waitlist entry
    @Transactional
    public WaitlistEntry createGuestAndWaitlistEntry(Guest guest) {
        Guest newGuest = guestService.createGuest(guest);
        WaitlistEntry entry = new WaitlistEntry(newGuest, WaitlistStatus.WAITING, LocalDateTime.now());

        return waitlistEntryRepository.save(entry);
    }

    // retrieve waitlist entry by guest name
    public WaitlistEntry getWaitlistEntryByGuestName(String guestName) {
        return waitlistEntryRepository.findWaitlistEntryByGuestName(guestName).orElse(null);
    }

    // retrieve all waitlist entries
    public List<WaitlistEntry> getWaitlist() {
        return waitlistEntryRepository.findAll();
    }

    // update waitlist entry status to NOTIFIED by guest name
    @Transactional
    public WaitlistEntry updateWaitlistEntryByGuestName(String guestName) {
        WaitlistEntry entry = getWaitlistEntryByGuestName(guestName);
        if (entry == null) {
            throw new IllegalArgumentException("Waitlist entry not found for guest: " + guestName);
        }
        entry.setStatus(WaitlistStatus.NOTIFIED);
        entry.setNotifiedTime(LocalDateTime.now());
        return waitlistEntryRepository.save(entry);
    }

    // delete guest and waitlist entry by guest name
    @Transactional
    public void deleteGuestAndWaitlistEntry(String guestName) {
        WaitlistEntry entry = getWaitlistEntryByGuestName(guestName);
        if (entry == null) {
            throw new IllegalArgumentException("Waitlist entry not found for guest: " + guestName);
        }
        waitlistEntryRepository.delete(entry);
        guestService.deleteGuestByName(guestName);
    }
}