package com.olivechip.restaurant_waitlist.dto;

import com.enums.StaffRole;

public class StaffDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private StaffRole role;

    public StaffDTO() {
    }

    public StaffDTO(Integer id, String firstName, String lastName, String username, StaffRole role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
