package com.microservice.authentication.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String birthDate;
    private String region;
    private String commune;
    private String address;
}
