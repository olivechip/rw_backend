package com.olivechip.restaurant_waitlist.dto;

import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.entity.Staff;
import lombok.Data;

@Data
public class RestaurantCreationRequest {
    private Restaurant restaurant;
    private Staff adminStaff;
}