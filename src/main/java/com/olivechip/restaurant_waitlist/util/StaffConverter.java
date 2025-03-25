package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.StaffDTO;
import com.olivechip.restaurant_waitlist.entity.Staff;

public class StaffConverter {

    public static StaffDTO convertToStaffDto(Staff staff) {
        return new StaffDTO(
                staff.getId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getUsername(),
                staff.getRole(),
                staff.getRestaurant().getId());
    }
}