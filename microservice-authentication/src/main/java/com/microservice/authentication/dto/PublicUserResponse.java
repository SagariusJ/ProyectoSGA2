package com.microservice.authentication.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicUserResponse {
    private Long id;
    private String username;
    private String fullName;
}
