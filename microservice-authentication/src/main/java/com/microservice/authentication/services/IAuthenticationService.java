package com.microservice.authentication.services;

import com.microservice.authentication.dto.RegisterRequest;

public interface IAuthenticationService {
    String login(String username, String password);
    void register(RegisterRequest request);

}