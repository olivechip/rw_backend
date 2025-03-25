package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.AuthStatusDTO;
import com.olivechip.restaurant_waitlist.dto.StaffDTO;
import com.olivechip.restaurant_waitlist.dto.LoginResponseDTO;
import com.olivechip.restaurant_waitlist.entity.Staff;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthConverter {

    public static AuthStatusDTO convertToAuthStatusDto(UserDetails userDetails, boolean isLoggedIn) {
        if (isLoggedIn && userDetails instanceof Staff) {
            Staff staff = (Staff) userDetails;
            StaffDTO staffDTO = new StaffDTO(
                    staff.getId(),
                    staff.getFirstName(),
                    staff.getLastName(),
                    staff.getUsername(),
                    staff.getRole(),
                    staff.getRestaurant().getId());
            return new AuthStatusDTO(true, staffDTO);
        } else {
            return new AuthStatusDTO(false, null);
        }
    }

    public static LoginResponseDTO convertToLoginResponseDto(Staff staff) {
        return new LoginResponseDTO(
                "Login successful",
                staff.getId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getUsername(),
                staff.getRole(),
                staff.getRestaurant().getId());
    }
}