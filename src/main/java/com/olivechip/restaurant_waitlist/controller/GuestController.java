package com.olivechip.restaurant_waitlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.entity.Guest;
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
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }
   
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable int id) {
        Guest guest = guestService.getGuestById(id);
        if (guest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guest);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Guest> getGuestByName(@PathVariable String name) {
        Guest guest = guestService.getGuestByName(name);
        if (guest == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(guest);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Guest> updateGuestByName(@PathVariable String name, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuestByName(name, guest);
        return ResponseEntity.ok(updatedGuest);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteGuestByName(@PathVariable String name) {
        guestService.deleteGuestByName(name);
        return ResponseEntity.noContent().build();
    }
}