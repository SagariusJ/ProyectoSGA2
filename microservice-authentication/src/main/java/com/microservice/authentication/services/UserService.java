package com.microservice.authentication.services;

import com.microservice.authentication.dto.PublicUserResponse;
import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.entities.User;
import com.microservice.authentication.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.microservice.authentication.dto.UserResponse;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void updateUserRole(Long userId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(role);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUserData(Long userId, RegisterRequest data) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(data.getFullName());
        user.setEmail(data.getEmail());
        user.setBirthDate(data.getBirthDate());
        user.setRegion(data.getRegion());
        user.setCommune(data.getCommune());
        user.setAddress(data.getAddress());

        userRepository.save(user);
    }

    public void updateUserDataByUsername(String username, RegisterRequest data) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(data.getFullName());
        user.setEmail(data.getEmail());
        user.setBirthDate(data.getBirthDate());
        user.setRegion(data.getRegion());
        user.setCommune(data.getCommune());
        user.setAddress(data.getAddress());

        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers() {
        Iterable<User> iterable = userRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getBirthDate(),
                        user.getRegion(),
                        user.getCommune(),
                        user.getAddress(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }

    public PublicUserResponse getPublicUserById(Long userId) {
        System.out.println("Buscando usuario por ID: " + userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado con ID: " + userId);
                    return new RuntimeException("User not found");
                });

        System.out.println("Usuario encontrado: " + user.getUsername());
        return new PublicUserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName()
        );
    }

    public List<UserResponse> getAllNonAdminUsers() {
        Iterable<User> iterable = userRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getBirthDate(),
                        user.getRegion(),
                        user.getCommune(),
                        user.getAddress(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }


}