package com.olivechip.restaurant_waitlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.enums.WaitlistStatus;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.service.WaitlistEntryService;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistEntryController {

    @Autowired
    private WaitlistEntryService waitlistEntryService;

    @PostMapping("/create")
    public ResponseEntity<WaitlistEntry> createGuestAndWaitlistEntry(@RequestBody Guest guest) {
        WaitlistEntry createdEntry = waitlistEntryService.createGuestAndWaitlistEntry(guest);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WaitlistEntry>> getWaitlist() {
        List<WaitlistEntry> waitlist = waitlistEntryService.getWaitlist();
        return ResponseEntity.ok(waitlist);
    }

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