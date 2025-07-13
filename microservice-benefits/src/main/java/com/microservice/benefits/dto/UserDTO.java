package com.microservice.benefits.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private String birthDate;
    private String region;
    private String commune;
    private String address;

}
