package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.WaitlistEntryDTO;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;

public class WaitlistEntryConverter {

    public static WaitlistEntryDTO convertToWaitlistEntryDto(WaitlistEntry entry) {
        return new WaitlistEntryDTO(
                entry.getId(),
                entry.getStatus(),
                entry.getJoinTime(),
                entry.getNotifiedTime(),
                entry.getCompletedTime(),
                entry.getCanceledTime(),
                entry.getRestaurant().getId(),
                entry.getGuest().getId());
    }
}