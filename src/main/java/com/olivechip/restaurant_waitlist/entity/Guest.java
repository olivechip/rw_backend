package com.olivechip.restaurant_waitlist.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<WaitlistEntry> waitlistEntries;

    public Guest(String name, Integer partySize, String phoneNumber) {
        this.name = name;
        this.partySize = partySize;
        this.phoneNumber = phoneNumber;
    }
}