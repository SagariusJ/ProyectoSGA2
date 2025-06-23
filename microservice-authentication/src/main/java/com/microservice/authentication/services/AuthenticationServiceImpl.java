package com.microservice.authentication.services;

import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.dto.UserDTO;
import com.microservice.authentication.entities.User;
import com.microservice.authentication.persistence.UserRepository;
import com.microservice.authentication.security.JwtUtil;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    private final String PATIENT_SYNC_URL = "https://<benefits-service>.railway.app/patients/sync";

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

        if ("ROLE_PATIENT".equalsIgnoreCase(user.getRole())) {
            UserDTO dto = new UserDTO();
            dto.setFullName(user.getFullName());
            dto.setBirthDate(user.getBirthDate());
            dto.setRegion(user.getRegion());
            dto.setCommune(user.getCommune());
            dto.setAddress(user.getAddress());

            try {
                restTemplate.postForEntity(PATIENT_SYNC_URL, dto, Void.class);
            } catch (Exception e) {
                e.printStackTrace(); // Aquí puedes agregar logs o reintentos
            }
        }
    }
}