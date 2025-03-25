package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.dto.GuestDTO;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.service.GuestService;
import com.olivechip.restaurant_waitlist.util.GuestConverter;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/create")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody Guest guest) {
        Guest createdGuest = guestService.createGuest(guest);
        GuestDTO guestDTO = GuestConverter.convertToGuestDto(createdGuest);
        return new ResponseEntity<>(guestDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        List<GuestDTO> dtos = guests.stream()
                .map(GuestConverter::convertToGuestDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
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