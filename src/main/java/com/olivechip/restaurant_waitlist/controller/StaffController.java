package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.olivechip.restaurant_waitlist.dto.StaffDTO;
import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.service.StaffService;
import com.olivechip.restaurant_waitlist.util.StaffConverter;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<StaffDTO> createStaff(HttpServletRequest httpRequest,
            @RequestBody Map<String, Object> request) {
        Staff staff = new ObjectMapper().convertValue(request.get("staff"), Staff.class);
        Integer restaurantId = (Integer) request.get("restaurantId");

        Staff createdStaff = staffService.createStaff(staff, restaurantId);

        StaffDTO staffDTO = StaffConverter.convertToStaffDto(createdStaff);

        return new ResponseEntity<>(staffDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff(
            @RequestParam(value = "restaurantId", required = false) Integer restaurantId) {

        List<Staff> staffList;

        if (restaurantId == null) {
            staffList = staffService.getAllStaff();
        } else {
            staffList = staffService.getStaffByRestaurantId(restaurantId);
        }

        List<StaffDTO> dtos = staffList.stream()
                .map(StaffConverter::convertToStaffDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
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