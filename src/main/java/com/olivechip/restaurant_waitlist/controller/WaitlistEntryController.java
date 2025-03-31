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

    @PostMapping("/create")
    public ResponseEntity<WaitlistEntryDTO> createGuestAndWaitlistEntry(
            @RequestBody Guest guest,
            @RequestParam("restaurantId") Integer restaurantId) {
        WaitlistEntry createdEntry = waitlistEntryService.createGuestAndWaitlistEntry(guest, restaurantId);

        WaitlistEntryDTO dto = WaitlistEntryConverter.convertToWaitlistEntryDto(createdEntry);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WaitlistEntryDTO>> getWaitlist() {

        List<WaitlistEntry> waitlist = waitlistEntryService.getWaitlist();

        List<WaitlistEntryDTO> dtos = waitlist.stream()
                .map(WaitlistEntryConverter::convertToWaitlistEntryDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<WaitlistEntryDTO> getWaitlistEntryById(@PathVariable Integer id) {
        WaitlistEntry entry = waitlistEntryService.getWaitlistEntryById(id);
        if (entry == null) {
            return ResponseEntity.notFound().build();
        }
        WaitlistEntryDTO dto = WaitlistEntryConverter.convertToWaitlistEntryDto(entry);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WaitlistEntryDTO> updateWaitlistEntryById(
            @PathVariable Integer id,
            @RequestBody WaitlistStatus status) {
        WaitlistEntry updatedEntry = waitlistEntryService.updateWaitlistEntryById(id, status);
        WaitlistEntryDTO dto = WaitlistEntryConverter.convertToWaitlistEntryDto(updatedEntry);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuestAndWaitlistEntry(@PathVariable Integer id) {
        waitlistEntryService.deleteGuestAndWaitlistEntry(id);
        return ResponseEntity.noContent().build();
    }
}