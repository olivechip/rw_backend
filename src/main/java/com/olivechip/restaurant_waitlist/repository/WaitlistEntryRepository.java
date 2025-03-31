package com.olivechip.restaurant_waitlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;

@Repository
public interface WaitlistEntryRepository extends JpaRepository<WaitlistEntry, Integer> {
    List<WaitlistEntry> findWaitlistEntriesByRestaurantId(Integer restaurantId);
}