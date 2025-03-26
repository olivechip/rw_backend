package com.olivechip.restaurant_waitlist.dto;

public class AuthStatusDTO {
    private boolean isLoggedIn;
    private StaffDTO staff;
    private RestaurantDTO restaurant;

    public AuthStatusDTO() {
    }

    public AuthStatusDTO(boolean isLoggedIn, StaffDTO staff, RestaurantDTO restaurant) {
        this.isLoggedIn = isLoggedIn;
        this.staff = staff;
        this.restaurant = restaurant;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
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