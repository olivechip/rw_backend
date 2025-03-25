package com.olivechip.restaurant_waitlist.dto;

import java.time.LocalDateTime;

public class WaitlistEntryDTO {

    private Integer id;
    private String status;
    private LocalDateTime joinTime;
    private LocalDateTime notifiedTime;
    private LocalDateTime completedTime;
    private LocalDateTime canceledTime;
    private Integer restaurantId;
    private Integer guestId;

    public WaitlistEntryDTO() {
    }

    public WaitlistEntryDTO(Integer id, String status,
            LocalDateTime joinTime, LocalDateTime notifiedTime, LocalDateTime completedTime,
            LocalDateTime canceledTime, Integer restaurantId, Integer guestId) {

        this.id = id;
        this.status = status;
        this.joinTime = joinTime;
        this.notifiedTime = notifiedTime;
        this.completedTime = completedTime;
        this.canceledTime = canceledTime;
        this.restaurantId = restaurantId;
        this.guestId = guestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public Integer getGuestId() {
        return guestId;
    }

    public void setGuestId(Integer guestId) {
        this.guestId = guestId;
    }
}