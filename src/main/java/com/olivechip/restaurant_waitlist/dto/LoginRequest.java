package com.olivechip.restaurant_waitlist.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}