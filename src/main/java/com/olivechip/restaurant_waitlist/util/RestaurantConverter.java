package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.RestaurantDTO;
import com.olivechip.restaurant_waitlist.entity.Restaurant;

public class RestaurantConverter {

    public static RestaurantDTO convertToRestaurantDto(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getEmail(),
                restaurant.getAddress(),
                restaurant.getPhoneNumber(),
                restaurant.getCuisineType(),
                restaurant.getWebsite(),
                restaurant.getDescription(),
                restaurant.getHoursOfOperation());
    }
}