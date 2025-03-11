package com.olivechip.restaurant_waitlist.dto;

import com.olivechip.restaurant_waitlist.entity.Staff;
import lombok.Data;

@Data
public class StaffCreationRequest {
    private Staff staff;
    private Integer restaurantId;
}
