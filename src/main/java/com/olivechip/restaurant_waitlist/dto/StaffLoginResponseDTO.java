package com.olivechip.restaurant_waitlist.dto;

import com.enums.StaffRole;

public class StaffLoginResponseDTO {
    private String message;
    private String firstName;
    private String lastName;
    private String username;
    private StaffRole role;
    private Integer restaurantId;

    public StaffLoginResponseDTO() {
    }

    public StaffLoginResponseDTO(String message, String firstName, String lastName, String username, StaffRole role,
            Integer restaurantId) {

        this.message = message;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.restaurantId = restaurantId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
