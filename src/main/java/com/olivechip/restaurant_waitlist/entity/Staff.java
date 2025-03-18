package com.olivechip.restaurant_waitlist.entity;

import jakarta.persistence.*;
import lombok.*;

import com.enums.StaffRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "staff")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false) // hash later
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private StaffRole role;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonIgnore
    private Restaurant restaurant;

    public Staff(String firstName, String lastName, String username, String password, StaffRole role,
            Restaurant restaurant) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.restaurant = restaurant;
    }
}
