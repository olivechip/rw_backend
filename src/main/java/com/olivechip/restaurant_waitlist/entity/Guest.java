package com.olivechip.restaurant_waitlist.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // annotates this class as a JPA entity
@Table(name = "guests") // specifies the entitiy maps to a "guests" table

// lombok boilerplate methods
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// guest entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "party_size", nullable = false)
    private Integer partySize;

    @Column(name = "phone_number", nullable = false) // validation later
    private String phoneNumber;

    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
    private WaitlistEntry waitlistEntry;
}