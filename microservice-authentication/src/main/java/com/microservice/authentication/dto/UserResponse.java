package com.microservice.authentication.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String birthDate;
    private String region;
    private String commune;
    private String address;
    private String role;
}