package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.GuestDTO;
import com.olivechip.restaurant_waitlist.entity.Guest;

public class GuestConverter {

    public static GuestDTO convertToGuestDto(Guest guest) {
        return new GuestDTO(
                guest.getId(),
                guest.getName(),
                guest.getPartySize(),
                guest.getPhoneNumber());
    }
}