package com.microservice.authentication.controller;

import com.microservice.authentication.dto.LoginRequest;
import com.microservice.authentication.dto.LoginResponse;
import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.dto.UserResponse;
import com.microservice.authentication.services.IAuthenticationService;
import com.microservice.authentication.services.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequest request) {
        System.out.println("Register request received: " + request);
        authService.register(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");

        return ResponseEntity.ok(response);
    }



    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        System.out.println("LLEGO A LOGIN CONTROLLER");
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Usuario autenticado: " + authentication.getName());
        System.out.println("Roles: " + authentication.getAuthorities());
        return userService.getAllUsers();
    }

}