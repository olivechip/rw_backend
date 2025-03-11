package com.olivechip.restaurant_waitlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.olivechip.restaurant_waitlist.entity.Restaurant;
import com.olivechip.restaurant_waitlist.service.RestaurantService;
import com.olivechip.restaurant_waitlist.dto.RestaurantCreationRequest;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody RestaurantCreationRequest request) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(request.getRestaurant(),
                request.getStaff());
        return new ResponseEntity<>(createdRestaurant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

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