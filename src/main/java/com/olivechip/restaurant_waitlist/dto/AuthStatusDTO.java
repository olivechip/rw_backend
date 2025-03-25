package com.olivechip.restaurant_waitlist.dto;

public class AuthStatusDTO {
    private boolean isLoggedIn;
    private StaffDTO staff;

    public AuthStatusDTO() {
    }

    public AuthStatusDTO(boolean isLoggedIn, StaffDTO staff) {
        this.isLoggedIn = isLoggedIn;
        this.staff = staff;
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
}