package com.olivechip.restaurant_waitlist.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.dto.RestaurantDTO;
import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        List<RestaurantDTO> dtos = restaurants.stream()
                .map(this::convertToRestaurantDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    private RestaurantDTO convertToRestaurantDto(Restaurant restaurant) {
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

    // this needs to return a RestaurantDTO or check if even needed
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Integer id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurantById(
            @PathVariable Integer id,
            @RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurantById(id, restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable Integer id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
}