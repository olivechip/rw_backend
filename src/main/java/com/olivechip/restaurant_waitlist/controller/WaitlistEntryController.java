package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enums.WaitlistStatus;
import com.olivechip.restaurant_waitlist.dto.WaitlistEntryDTO;
import com.olivechip.restaurant_waitlist.dto.WaitlistWithGuestDTO;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.service.GuestService;
import com.olivechip.restaurant_waitlist.service.WaitlistEntryService;
import com.olivechip.restaurant_waitlist.util.WaitlistEntryConverter;
import com.olivechip.restaurant_waitlist.util.WaitlistWithGuestConverter;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistEntryController {

    @Autowired
    private WaitlistEntryService waitlistEntryService;

    @Autowired
    private GuestService guestService;

    // create a guest and waitlist entry
    @PostMapping("/create")
    public ResponseEntity<WaitlistEntryDTO> createGuestAndWaitlistEntry(
            @RequestBody Guest guest,
            @RequestParam("restaurantId") Integer restaurantId) {
        WaitlistEntry createdEntry = waitlistEntryService.createGuestAndWaitlistEntry(guest, restaurantId);

        WaitlistEntryDTO dto = WaitlistEntryConverter.convertToWaitlistEntryDto(createdEntry);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // get all waitlist entries
    @GetMapping
    public ResponseEntity<List<WaitlistEntryDTO>> getWaitlist() {

        List<WaitlistEntry> waitlist = waitlistEntryService.getWaitlist();

        List<WaitlistEntryDTO> dtos = waitlist.stream()
                .map(WaitlistEntryConverter::convertToWaitlistEntryDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // get waitlist by restaurant id
    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<WaitlistWithGuestDTO>> getWaitlistByRestaurant(
            @PathVariable("restaurantId") Integer restaurantId) {

        List<WaitlistEntry> waitlist = waitlistEntryService.getWaitlistByRestaurantId(restaurantId);

        List<WaitlistWithGuestDTO> dtos = waitlist.stream()
                .map(entry -> {
                    Guest guest = guestService.getGuestById(entry.getGuest().getId());
                    return WaitlistWithGuestConverter.convertToWaitlistWithGuestDto(entry, guest);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    // DTO checks starting here
    @GetMapping("/{guestId}")
    public ResponseEntity<WaitlistEntry> getWaitlistEntryByGuestId(@PathVariable Integer guestId) {
        WaitlistEntry entry = waitlistEntryService.getWaitlistEntryByGuestId(guestId);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entry);
    }

    @PutMapping("/{guestId}/{status}")
    public ResponseEntity<WaitlistEntry> updateWaitlistEntryByGuestId(
            @PathVariable Integer guestId,
            @PathVariable WaitlistStatus status) {
        WaitlistEntry updatedEntry = waitlistEntryService.updateWaitlistEntryByGuestId(guestId, status);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<Void> deleteGuestAndWaitlistEntry(@PathVariable Integer guestId) {
        waitlistEntryService.deleteGuestAndWaitlistEntry(guestId);
        return ResponseEntity.noContent().build();
    }
}