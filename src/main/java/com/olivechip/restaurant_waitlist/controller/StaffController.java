package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.service.StaffService;
import com.olivechip.restaurant_waitlist.repository.RestaurantRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(HttpServletRequest httpRequest, @RequestBody Map<String, Object> request) {
        Staff staff = new ObjectMapper().convertValue(request.get("staff"), Staff.class);
        Integer restaurantId = (Integer) request.get("restaurantId");

        Staff createdStaff = staffService.createStaff(staff, restaurantId);

        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    // **********************************
    // for testing only, remove in prod
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return ResponseEntity.ok(staffList);
    }
    // **********************************

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Integer id) {
        Staff staff = staffService.getStaffById(id);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaffById(
            @PathVariable Integer id,
            @RequestBody Staff staff) {
        Staff updatedStaff = staffService.updateStaffById(id, staff);
        return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaffById(@PathVariable Integer id) {
        staffService.deleteStaffById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Staff>> getStaffByRestaurant(
            @RequestParam Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }

        List<Staff> staffList = staffService.getStaffByRestaurant(restaurant);
        return ResponseEntity.ok(staffList);
    }
}