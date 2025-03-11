package com.olivechip.restaurant_waitlist.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurants")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder // added Builder annotation for easier object creation

public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false) // hash later
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cuisine_type")
    private String cuisineType;

    @Column(name = "website")
    private String website;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "hours_of_operation", columnDefinition = "TEXT")
    private String hoursOfOperation;

    public Restaurant(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
}