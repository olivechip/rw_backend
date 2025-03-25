package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.service.StaffService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(HttpServletRequest httpRequest, @RequestBody Map<String, Object> request) {
        Staff staff = new ObjectMapper().convertValue(request.get("staff"), Staff.class);
        Integer restaurantId = (Integer) request.get("restaurantId");

        Staff createdStaff = staffService.createStaff(staff, restaurantId);

        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff(
            @RequestParam(value = "restaurantId", required = false) Integer restaurantId) {

        List<Staff> staffList;

        if (restaurantId == null) {
            staffList = staffService.getAllStaff();
        } else {
            staffList = staffService.getStaffByRestaurantId(restaurantId);
        }

        return ResponseEntity.ok(staffList);
    }

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
}