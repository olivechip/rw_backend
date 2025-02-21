package com.olivechip.restaurant_waitlist.entity;

import jakarta.persistence.*;
import lombok.*;

import com.enums.StaffRole;

@Entity
@Table(name = "staff")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "pin", nullable = false) // hash later
    private String pin;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private StaffRole role;
}
