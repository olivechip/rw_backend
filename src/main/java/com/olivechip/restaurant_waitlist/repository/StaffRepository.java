package com.olivechip.restaurant_waitlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.entity.Restaurant;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    boolean existsByUsername(String username);
    List<Staff> findByRestaurant(Restaurant restaurant);
}