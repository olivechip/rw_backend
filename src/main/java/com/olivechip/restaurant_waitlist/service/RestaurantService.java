package com.olivechip.restaurant_waitlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // creates a restaurant
    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        // validate that required fields are not null
        if (restaurant.getName() == null || restaurant.getEmail() == null ||
                restaurant.getAddress() == null || restaurant.getPhoneNumber() == null) {
            throw new IllegalArgumentException(
                    "Restaurant must have name, email, address, and phone number");
        }

        // check for duplicate restaurant name, email, or phone number
        if (restaurantRepository.existsByName(restaurant.getName())) {
            throw new IllegalArgumentException("Restaurant with this name already exists");
        }

        if (restaurantRepository.existsByEmail(restaurant.getEmail())) {
            throw new IllegalArgumentException("Restaurant with this email already exists");
        }

        if (restaurantRepository.existsByPhoneNumber(restaurant.getPhoneNumber())) {
            throw new IllegalArgumentException("Restaurant with this phone number already exists");
        }

        // if no duplicates are found, save the restaurant
        return restaurantRepository.save(restaurant);
    }

    // retrieve all restaurants
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // retrieve restaurant by id
    public Restaurant getRestaurantById(Integer id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    // update restaurant if restaurant exists by id
    public Restaurant updateRestaurantById(Integer id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + id));

        if (restaurant.getName() != null) {
            existingRestaurant.setName(restaurant.getName());
        }
        if (restaurant.getAddress() != null) {
            existingRestaurant.setAddress(restaurant.getAddress());
        }
        if (restaurant.getPhoneNumber() != null) {
            existingRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        }
        if (restaurant.getEmail() != null) {
            existingRestaurant.setEmail(restaurant.getEmail());
        }
        if (restaurant.getCuisineType() != null) {
            existingRestaurant.setCuisineType(restaurant.getCuisineType());
        }
        if (restaurant.getWebsite() != null) {
            existingRestaurant.setWebsite(restaurant.getWebsite());
        }
        if (restaurant.getDescription() != null) {
            existingRestaurant.setDescription(restaurant.getDescription());
        }
        if (restaurant.getHoursOfOperation() != null) {
            existingRestaurant.setHoursOfOperation(restaurant.getHoursOfOperation());
        }

        return restaurantRepository.save(existingRestaurant);
    }

    // delete restaurant if restaurant exists by id
    public void deleteRestaurantById(Integer id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + id));
        restaurantRepository.delete(existingRestaurant);
    }
}