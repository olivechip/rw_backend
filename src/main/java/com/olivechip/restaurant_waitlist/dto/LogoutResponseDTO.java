package com.olivechip.restaurant_waitlist.dto;

public class LogoutResponseDTO {
    private String message;

    public LogoutResponseDTO() {
    }

    public LogoutResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}