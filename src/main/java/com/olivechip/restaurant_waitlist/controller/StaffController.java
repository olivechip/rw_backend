package com.olivechip.restaurant_waitlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.dto.StaffCreationRequest;
import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(@RequestBody StaffCreationRequest request) {
        Staff createdStaff = staffService.createStaff(request.getStaff(), request.getRestaurantId());
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    // **********************************
    // for testing only, remove in prod
    @GetMapping("/all")
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

    @GetMapping
    public ResponseEntity<List<Staff>> getStaffByRestaurant(
            @RequestParam Integer restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);

        List<Staff> staffList = staffService.getStaffByRestaurant(restaurant);
        return ResponseEntity.ok(staffList);
    }
}