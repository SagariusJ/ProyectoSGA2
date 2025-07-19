package com.microservice.benefits.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservice.benefits.dto.UserDTO;

@FeignClient(name = "microservice-authentication", url= "https://microservice-authentication-production.up.railway.app")
public interface UserClient {
    @GetMapping("/api/auth/public/user/{userId}")
    UserDTO getUserById(@PathVariable("userId") Long userId);
}
