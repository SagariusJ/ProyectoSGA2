package com.microservice.benefits.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservice.benefits.dto.UserDTO;

@FeignClient(name = "microservice-authentication")
public interface UserClient {

    @GetMapping("/api/auth/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);
}
