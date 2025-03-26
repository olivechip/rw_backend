package com.olivechip.restaurant_waitlist.dto;

public class LoginResponseDTO {

    private String message;
    private StaffDTO staff;
    private RestaurantDTO restaurant;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String message, StaffDTO staff, RestaurantDTO restaurant) {
        this.message = message;
        this.staff = staff;
        this.restaurant = restaurant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO staff) {
        this.staff = staff;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}