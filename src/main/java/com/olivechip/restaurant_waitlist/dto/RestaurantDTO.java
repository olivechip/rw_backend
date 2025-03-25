package com.olivechip.restaurant_waitlist.dto;

import java.util.List;

public class RestaurantDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String cuisineType;
    private String website;
    private String description;
    private String hoursOfOperation;
    private List<StaffDTO> staff;
    private List<WaitlistEntryCombinedDTO> waitlistEntries;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Integer id, String name, String email, String address, String phoneNumber,
            String cuisineType, String website, String description, String hoursOfOperation,
            List<StaffDTO> staff, List<WaitlistEntryCombinedDTO> waitlistEntries) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cuisineType = cuisineType;
        this.website = website;
        this.description = description;
        this.hoursOfOperation = hoursOfOperation;
        this.staff = staff;
        this.waitlistEntries = waitlistEntries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public List<StaffDTO> getStaff() {
        return staff;
    }

    public void setStaff(List<StaffDTO> staff) {
        this.staff = staff;
    }

    public List<WaitlistEntryCombinedDTO> getWaitlistEntries() {
        return waitlistEntries;
    }

    public void setWaitlistEntries(List<WaitlistEntryCombinedDTO> waitlistEntries) {
        this.waitlistEntries = waitlistEntries;
    }
}
