package com.olivechip.restaurant_waitlist.dto;

import java.time.LocalDateTime;

import com.enums.WaitlistStatus;

public class WaitlistWithGuestDTO {

    private Integer id;
    private WaitlistStatus status;
    private LocalDateTime joinTime;
    private LocalDateTime notifiedTime;
    private LocalDateTime completedTime;
    private LocalDateTime canceledTime;
    private Integer restaurantId;
    private GuestDTO guest;

    public WaitlistWithGuestDTO() {
    }

    public WaitlistWithGuestDTO(Integer id, WaitlistStatus status,
            LocalDateTime joinTime, LocalDateTime notifiedTime, LocalDateTime completedTime,
            LocalDateTime canceledTime, Integer restaurantId, GuestDTO guest) {

        this.id = id;
        this.status = status;
        this.joinTime = joinTime;
        this.notifiedTime = notifiedTime;
        this.completedTime = completedTime;
        this.canceledTime = canceledTime;
        this.restaurantId = restaurantId;
        this.guest = guest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WaitlistStatus getStatus() {
        return status;
    }

    public void setStatus(WaitlistStatus status) {
        this.status = status;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    public LocalDateTime getNotifiedTime() {
        return notifiedTime;
    }

    public void setNotifiedTime(LocalDateTime notifiedTime) {
        this.notifiedTime = notifiedTime;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }

    public LocalDateTime getCanceledTime() {
        return canceledTime;
    }

    public void setCanceledTime(LocalDateTime canceledTime) {
        this.canceledTime = canceledTime;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public GuestDTO getGuest() {
        return guest;
    }

    public void setGuest(GuestDTO guest) {
        this.guest = guest;
    }

    public static class GuestDTO {
        private Integer id;
        private String name;
        private Integer partySize;
        private String phoneNumber;

        public GuestDTO(Integer id, String name, Integer partySize, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.partySize = partySize;
            this.phoneNumber = phoneNumber;
        }

        public GuestDTO() {
        };

        public Integer getId() {
            return id;
        };

        public void setId(Integer id) {
            this.id = id;
        };

        public String getName() {
            return name;
        };

        public void setName(String name) {
            this.name = name;
        };

        public Integer getPartySize() {
            return partySize;
        };

        public void setPartySize(Integer partySize) {
            this.partySize = partySize;
        };

        public String getPhoneNumber() {
            return phoneNumber;
        };

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        };
    }
}
