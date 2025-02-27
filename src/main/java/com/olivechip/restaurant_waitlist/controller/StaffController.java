package com.olivechip.restaurant_waitlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff createdStaff = staffService.createStaff(staff);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int id) {
        Staff staff = staffService.getStaffById(id);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Staff> getStaffByUsername(@PathVariable String username) {
        Staff staff = staffService.getStaffByUsername(username);
        if (staff == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Staff> updateStaffByUsername(@PathVariable String username, @RequestBody Staff staff) {
        Staff updatedStaff = staffService.updateStaffByUsername(username, staff);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteStaffByUsername(@PathVariable String username) {
        staffService.deleteStaffByUsername(username);
        return ResponseEntity.noContent().build();
    }
}