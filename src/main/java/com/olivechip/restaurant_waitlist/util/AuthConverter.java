package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.AuthStatusDTO;
import com.olivechip.restaurant_waitlist.dto.RestaurantDTO;
import com.olivechip.restaurant_waitlist.dto.StaffDTO;
import com.olivechip.restaurant_waitlist.dto.LoginResponseDTO;
import com.olivechip.restaurant_waitlist.entity.Restaurant;
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

            Restaurant restaurant = staff.getRestaurant();
            RestaurantDTO restaurantDTO = new RestaurantDTO(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getEmail(),
                    restaurant.getAddress(),
                    restaurant.getPhoneNumber(),
                    restaurant.getCuisineType(),
                    restaurant.getWebsite(),
                    restaurant.getDescription(),
                    restaurant.getHoursOfOperation());
            return new AuthStatusDTO(true, staffDTO, restaurantDTO);
        } else {
            return new AuthStatusDTO(false, null, null);
        }
    }

    public static LoginResponseDTO convertToLoginResponseDto(Staff staff) {
        StaffDTO staffDTO = new StaffDTO(
                staff.getId(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getUsername(),
                staff.getRole(),
                staff.getRestaurant().getId());

        Restaurant restaurant = staff.getRestaurant();
        RestaurantDTO restaurantDTO = new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getEmail(),
                restaurant.getAddress(),
                restaurant.getPhoneNumber(),
                restaurant.getCuisineType(),
                restaurant.getWebsite(),
                restaurant.getDescription(),
                restaurant.getHoursOfOperation());
                
        return new LoginResponseDTO("Login successful", staffDTO, restaurantDTO);
    }
}