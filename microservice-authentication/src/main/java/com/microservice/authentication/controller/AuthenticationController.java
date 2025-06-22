package com.microservice.authentication.controller;

import com.microservice.authentication.dto.LoginRequest;
import com.microservice.authentication.dto.LoginResponse;
import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.dto.UserResponse;
import com.microservice.authentication.services.IAuthenticationService;
import com.microservice.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authService;

    @Autowired
    private UserService userService;


    @PostMapping("/auth/register")
    public String register(@RequestBody RegisterRequest request) {
        System.out.println("Register endpoint hit with user: " + request.getUsername());
        authService.register(request);
        return "User registered successfully";
    }


    @PostMapping("/auth/login")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public LoginResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getUsername(), request.getPassword());
        return new LoginResponse(token);
    }

    @PutMapping("/auth/edit-profile")
    public String editProfile(@RequestBody RegisterRequest request, Principal principal) {
        userService.updateUserDataByUsername(principal.getName(), request);
        return "Profile updated";
    }

    @PutMapping("/auth/admin/update-role/{userId}")
    public String updateRole(@PathVariable Long userId, @RequestParam String role) {
        userService.updateUserRole(userId, role);
        return "Role updated";
    }

    @DeleteMapping("/auth/admin/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "User deleted";
    }

    @PutMapping("/auth/admin/update-user/{userId}")
    public String updateUserData(@PathVariable Long userId, @RequestBody RegisterRequest updatedData) {
        userService.updateUserData(userId, updatedData);
        return "User data updated";
    }

    @GetMapping("/auth/admin/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

}