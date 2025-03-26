package com.olivechip.restaurant_waitlist.util;

import com.olivechip.restaurant_waitlist.dto.WaitlistWithGuestDTO;
import com.olivechip.restaurant_waitlist.entity.Guest;
import com.olivechip.restaurant_waitlist.entity.WaitlistEntry;

public class WaitlistWithGuestConverter {

    public static WaitlistWithGuestDTO convertToWaitlistWithGuestDto(WaitlistEntry entry, Guest guest) {
        if (entry == null || guest == null) {
            return null;
        }

        WaitlistWithGuestDTO.GuestDTO guestDTO = new WaitlistWithGuestDTO.GuestDTO(
                guest.getId(),
                guest.getName(),
                guest.getPartySize(),
                guest.getPhoneNumber());

        return new WaitlistWithGuestDTO(
                entry.getId(),
                entry.getStatus(),
                entry.getJoinTime(),
                entry.getNotifiedTime(),
                entry.getCompletedTime(),
                entry.getCanceledTime(),
                entry.getRestaurant().getId(),
                guestDTO);
    }
}