package com.microservice.authentication.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String birthDate;

    @Column
    private String region;

    @Column
    private String commune;

    @Column
    private String address;

    @Column
    private String role; // e.g., "ROLE_USER", "ROLE_ADMIN"
}