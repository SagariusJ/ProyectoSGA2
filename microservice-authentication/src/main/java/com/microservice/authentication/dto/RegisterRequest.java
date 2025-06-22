package com.microservice.authentication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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