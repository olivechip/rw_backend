package com.olivechip.restaurant_waitlist.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.WaitlistStatus;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.repository.RestaurantRepository;
import com.olivechip.restaurant_waitlist.repository.WaitlistEntryRepository;

import jakarta.transaction.Transactional;

@Service
public class WaitlistEntryService {

    @Autowired
    private GuestService guestService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private WaitlistEntryRepository waitlistEntryRepository;

    // create a new guest and waitlist entry
    @Transactional
    public WaitlistEntry createGuestAndWaitlistEntry(Guest guest, Integer resId) {
        Guest newGuest = guestService.createGuest(guest);
        Restaurant restaurant = restaurantRepository.findById(resId).orElse(null);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found with id: " + resId);
        }
        WaitlistEntry entry = new WaitlistEntry(restaurant, newGuest, WaitlistStatus.WAITING, LocalDateTime.now());

        return waitlistEntryRepository.save(entry);
    }

    // retrieve waitlist entry by guest id
    public WaitlistEntry getWaitlistEntryByGuestId(Integer guestId) {
        return waitlistEntryRepository.findWaitlistEntryByGuestId(guestId).orElse(null);
    }

    // retrieve all waitlist entries
    public List<WaitlistEntry> getWaitlist() {
        return waitlistEntryRepository.findAll();
    }

    // update waitlist entry status by guest id AND new status
    @Transactional
    public WaitlistEntry updateWaitlistEntryByGuestId(Integer guestId, WaitlistStatus status) {
        WaitlistEntry entry = getWaitlistEntryByGuestId(guestId);
        if (entry == null) {
            throw new IllegalArgumentException("Waitlist entry not found for guest: " + guestId);
        }
        entry.setStatus(status);

        LocalDateTime now = LocalDateTime.now();

        switch (status) {
            case WAITING:
                entry.setJoinTime(now);
                break;
            case NOTIFIED:
                entry.setNotifiedTime(now);
                break;
            case COMPLETED:
                entry.setCompletedTime(now);
                break;
            case CANCELED:
                entry.setCanceledTime(now);
                break;
        }
        return waitlistEntryRepository.save(entry);
    }

    // delete guest and waitlist entry by guest id
    @Transactional
    public void deleteGuestAndWaitlistEntry(Integer guestId) {
        WaitlistEntry entry = getWaitlistEntryByGuestId(guestId);
        if (entry == null) {
            throw new IllegalArgumentException("Waitlist entry not found for guest: " + guestId);
        }
        waitlistEntryRepository.delete(entry);
        guestService.deleteGuestById(guestId);
    }
}