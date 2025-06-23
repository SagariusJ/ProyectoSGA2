package com.microservice.authentication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String fullName;
    private String birthDate;
    private String region;
    private String commune;
    private String address;
}
