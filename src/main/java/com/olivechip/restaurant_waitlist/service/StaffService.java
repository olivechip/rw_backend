package com.olivechip.restaurant_waitlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.repository.StaffRepository;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    // create a new staff member
    public Staff createStaff(Staff staff) {
        if (staff.getUsername() == null || staff.getPin() == null || staff.getRole() == null) {
            throw new IllegalArgumentException("Staff must have username, PIN, and role.");
        }
        if (staffRepository.existsByUsername(staff.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }
        return staffRepository.save(staff);
    }

    // retrieve staff member by id
    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id).orElse(null);
    }

    // retrieve staff member by username
    public Staff getStaffByUsername(String username) {
        return staffRepository.findByUsername(username).orElse(null);
    }

    // update staff member if exists by username
    public Staff updateStaffByUsername(String username, Staff staff) {
        Staff existingStaff = staffRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Staff member not found with username: " + username));

        if (staff.getUsername() != null && !staff.getUsername().equals(existingStaff.getUsername())) {
            if (staffRepository.existsByUsername(staff.getUsername())) {
                throw new IllegalArgumentException("Username already exists.");
            }
            existingStaff.setUsername(staff.getUsername());
        }
        if (staff.getPin() != null) {
            existingStaff.setPin(staff.getPin());
        }
        if (staff.getRole() != null) {
            existingStaff.setRole(staff.getRole());
        }

        return staffRepository.save(existingStaff);
    }

    // delete staff member if exists by username
    public void deleteStaffByUsername(String username) {
        Staff existingStaff = staffRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Staff member not found with username: " + username));
        staffRepository.delete(existingStaff);
    }
}