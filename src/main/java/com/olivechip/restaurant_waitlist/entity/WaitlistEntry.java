package com.olivechip.restaurant_waitlist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

import com.enums.WaitlistStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "waitlist_entries")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WaitlistEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private WaitlistStatus status;

    @Column(name = "join_time", nullable = false)
    private LocalDateTime joinTime;

    @Column(name = "notified_time")
    private LocalDateTime notifiedTime;

    @Column(name = "completed_time")
    private LocalDateTime completedTime;

    @Column(name = "canceled_time")
    private LocalDateTime canceledTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    // @JsonBackReference
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    @JsonBackReference
    private Guest guest;

    public WaitlistEntry(Restaurant restaurant, Guest guest, WaitlistStatus status, LocalDateTime joinTime) {
        this.restaurant = restaurant;
        this.guest = guest;
        this.status = status;
        this.joinTime = joinTime;
    }
}