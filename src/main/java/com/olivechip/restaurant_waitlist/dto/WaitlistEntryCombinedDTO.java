package com.olivechip.restaurant_waitlist.dto;

import java.time.LocalDateTime;

public class WaitlistEntryCombinedDTO {
    private Integer id;
    private Integer restaurantId;
    private Integer guestId;
    private String status;
    private LocalDateTime joinTime;
    private LocalDateTime notifiedTime;
    private LocalDateTime completedTime;
    private LocalDateTime canceledTime;

    // Constructors
    public WaitlistEntryCombinedDTO() {
    }

    public WaitlistEntryCombinedDTO(Integer id, Integer restaurantId, Integer guestId, String status,
            LocalDateTime joinTime, LocalDateTime notifiedTime, LocalDateTime completedTime,
            LocalDateTime canceledTime) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.guestId = guestId;
        this.status = status;
        this.joinTime = joinTime;
        this.notifiedTime = notifiedTime;
        this.completedTime = completedTime;
        this.canceledTime = canceledTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}