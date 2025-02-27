package com.olivechip.restaurant_waitlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.service.WaitlistEntryService;

import java.util.List;

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

    @PutMapping("/{guestName}")
    public ResponseEntity<WaitlistEntry> updateWaitlistEntryByGuestName(@PathVariable String guestName) {
        WaitlistEntry updatedEntry = waitlistEntryService.updateWaitlistEntryByGuestName(guestName);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{guestName}")
    public ResponseEntity<Void> deleteGuestAndWaitlistEntry(@PathVariable String guestName) {
        waitlistEntryService.deleteGuestAndWaitlistEntry(guestName);
        return ResponseEntity.noContent().build();
    }
}