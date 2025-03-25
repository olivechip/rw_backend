package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enums.WaitlistStatus;
import com.olivechip.restaurant_waitlist.dto.WaitlistEntryDTO;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.service.WaitlistEntryService;
import com.olivechip.restaurant_waitlist.util.WaitlistEntryConverter;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistEntryController {

    @Autowired
    private WaitlistEntryService waitlistEntryService;

    @PostMapping("/create")
    public ResponseEntity<WaitlistEntryDTO> createGuestAndWaitlistEntry(
            @RequestBody Guest guest,
            @RequestParam("restaurantId") Integer restaurantId) {
        WaitlistEntry createdEntry = waitlistEntryService.createGuestAndWaitlistEntry(guest, restaurantId);

        WaitlistEntryDTO dto = WaitlistEntryConverter.convertToWaitlistEntryDto(createdEntry);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WaitlistEntryDTO>> getWaitlist(
            @RequestParam(value = "restaurantId", required = false) Integer restaurantId) {

        List<WaitlistEntry> waitlist;

        if (restaurantId != null) {
            waitlist = waitlistEntryService.getWaitlistByRestaurantId(restaurantId);
        } else {
            waitlist = waitlistEntryService.getWaitlist();
        }

        List<WaitlistEntryDTO> dtos = waitlist.stream()
                .map(WaitlistEntryConverter::convertToWaitlistEntryDto)
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