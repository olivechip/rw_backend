package com.olivechip.restaurant_waitlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.repository.StaffRepository;
import com.olivechip.restaurant_waitlist.repository.RestaurantRepository;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // create a new staff member
    public Staff createStaff(Staff staff, Integer restaurantId) {
        if (staff.getFirstName() == null || staff.getLastName() == null || staff.getUsername() == null ||
                staff.getPassword() == null || staff.getRole() == null || restaurantId == null) {
            throw new IllegalArgumentException(
                    "Staff must have first name, last name, username, password, role, and restaurant ID");
        }

        // Find restaurant by ID
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantId));

        // Set the restaurant for the staff object
        staff.setRestaurant(restaurant);

        // Check if the username already exists
        if (staffRepository.existsByUsername(staff.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Save the staff member
        return staffRepository.save(staff);
    }

    // retrieve all staff members
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // retrieve staff member by id
    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id).orElse(null);
    }

    // update staff member if staff exists by id
    public Staff updateStaffById(Integer id, Staff staff) {
        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found with id: " + id));

        if (staff.getFirstName() != null) {
            existingStaff.setFirstName(staff.getFirstName());
        }
        if (staff.getLastName() != null) {
            existingStaff.setLastName(staff.getLastName());
        }
        if (staff.getUsername() != null) {
            // check if the new username is different from the existing one
            if (!existingStaff.getUsername().equals(staff.getUsername())) {
                // check if the new username already exists
                if (staffRepository.existsByUsername(staff.getUsername())) {
                    throw new IllegalArgumentException("Username already exists");
                }
                existingStaff.setUsername(staff.getUsername());
            }
        }
        if (staff.getPassword() != null) {
            existingStaff.setPassword(staff.getPassword());
        }
        if (staff.getRole() != null) {
            existingStaff.setRole(staff.getRole());
        }
        if (staff.getRestaurant() != null) {
            existingStaff.setRestaurant(staff.getRestaurant());
        }
        return staffRepository.save(existingStaff);
    }

    // delete staff member if staff exists by id
    public void deleteStaffById(Integer id) {
        Staff existingStaff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found with id: " + id));
        staffRepository.delete(existingStaff);
    }

    // retrieve staff members by restaurant
    public List<Staff> getStaffByRestaurant(Restaurant restaurant) {
        return staffRepository.findByRestaurant(restaurant);
    }
}