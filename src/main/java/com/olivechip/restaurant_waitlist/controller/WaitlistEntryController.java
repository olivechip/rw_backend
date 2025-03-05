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

    @GetMapping("/{guestName}")
    public ResponseEntity<WaitlistEntry> getWaitlistEntryByGuestName(@PathVariable String guestName) {
        WaitlistEntry entry = waitlistEntryService.getWaitlistEntryByGuestName(guestName);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entry);
    }

    @GetMapping
    public ResponseEntity<List<WaitlistEntry>> getWaitlist() {
        List<WaitlistEntry> waitlist = waitlistEntryService.getWaitlist();
        return ResponseEntity.ok(waitlist);
    }

    @PutMapping("/{guestName}/{status}")
    public ResponseEntity<WaitlistEntry> updateWaitlistEntryByGuestName(
            @PathVariable String guestName,
            @PathVariable WaitlistStatus status) {
        WaitlistEntry updatedEntry = waitlistEntryService.updateWaitlistEntryByGuestName(guestName, status);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{guestName}")
    public ResponseEntity<Void> deleteGuestAndWaitlistEntry(@PathVariable String guestName) {
        waitlistEntryService.deleteGuestAndWaitlistEntry(guestName);
        return ResponseEntity.noContent().build();
    }
}