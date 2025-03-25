package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.dto.GuestDTO;
import com.olivechip.restaurant_waitlist.dto.WaitlistEntryCombinedDTO;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;
import com.olivechip.restaurant_waitlist.service.GuestService;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/create")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest createdGuest = guestService.createGuest(guest);
        return new ResponseEntity<>(createdGuest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        List<GuestDTO> dtos = guests.stream().map(this::convertToGuestDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private GuestDTO convertToGuestDto(Guest guest) {
        List<WaitlistEntryCombinedDTO> waitlistEntries = guest.getWaitlistEntries().stream()
                .map(this::convertToCombinedDto)
                .collect(Collectors.toList());

        return new GuestDTO(
                guest.getId(),
                guest.getName(),
                guest.getPartySize(),
                guest.getPhoneNumber(),
                waitlistEntries);
    }

    private WaitlistEntryCombinedDTO convertToCombinedDto(WaitlistEntry entry) {
        return new WaitlistEntryCombinedDTO(
                entry.getId(),
                entry.getRestaurant().getId(),
                entry.getGuest().getId(),
                entry.getStatus().name(),
                entry.getJoinTime(),
                entry.getNotifiedTime(),
                entry.getCompletedTime(),
                entry.getCanceledTime());
    }

    // DTO checks starting here
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Integer id) {
        Guest guest = guestService.getGuestById(id);
        if (guest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuestById(@PathVariable Integer id, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuestById(id, guest);
        return ResponseEntity.ok(updatedGuest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuestById(@PathVariable Integer id) {
        guestService.deleteGuestById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Guest> getGuestByName(@PathVariable String name) {
        Guest guest = guestService.getGuestByName(name);
        if (guest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guest);
    }
}