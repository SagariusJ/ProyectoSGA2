package com.microservice.authentication.services;

import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.entities.User;
import com.microservice.authentication.persistence.UserRepository;
import com.microservice.authentication.security.JwtUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public String login(String username, String password) {
        var authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        var userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .region(request.getRegion())
                .commune(request.getCommune())
                .address(request.getAddress())
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
    }
}