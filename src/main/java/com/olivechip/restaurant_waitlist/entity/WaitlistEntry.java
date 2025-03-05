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

    // relationship and join annotation for JPA mapping used in JPA entities
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    @JsonBackReference
    private Guest guest;

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

    public WaitlistEntry(Guest guest, WaitlistStatus status, LocalDateTime joinTime) {
        this.guest = guest;
        this.status = status;
        this.joinTime = joinTime;
    }
}