package com.microservice.authentication.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.entities.User;
import com.microservice.authentication.persistence.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateUserRole_Success() {
        Long userId = 1L;
        User user = new User();
        user.setRole("ROLE_USER");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.updateUserRole(userId, "ROLE_ADMIN");

        assertEquals("ROLE_ADMIN", user.getRole());
        verify(userRepository).save(user);
    }

    @Test
    void updateUserRole_UserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.updateUserRole(userId, "ROLE_ADMIN");
        });

        assertEquals("User not found", ex.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void deleteUser_Success() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void updateUserData_Success() {
        Long userId = 1L;
        User user = new User();
        RegisterRequest req = RegisterRequest.builder()
                .fullName("New Name")
                .email("newemail@example.com")
                .birthDate("1990-01-01")
                .region("RegionX")
                .commune("CommuneY")
                .address("New Address")
                .build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.updateUserData(userId, req);

        assertEquals("New Name", user.getFullName());
        assertEquals("newemail@example.com", user.getEmail());
        assertEquals("1990-01-01", user.getBirthDate());
        assertEquals("RegionX", user.getRegion());
        assertEquals("CommuneY", user.getCommune());
        assertEquals("New Address", user.getAddress());

        verify(userRepository).save(user);
    }

    @Test
    void updateUserData_UserNotFound() {
        Long userId = 1L;
        RegisterRequest req = RegisterRequest.builder().build();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.updateUserData(userId, req);
        });

        assertEquals("User not found", ex.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void updateUserDataByUsername_Success() {
        String username = "user1";
        User user = new User();
        RegisterRequest req = RegisterRequest.builder()
                .fullName("Name2")
                .email("email2@example.com")
                .birthDate("1985-05-05")
                .region("Region2")
                .commune("Commune2")
                .address("Address2")
                .build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        userService.updateUserDataByUsername(username, req);

        assertEquals("Name2", user.getFullName());
        assertEquals("email2@example.com", user.getEmail());
        assertEquals("1985-05-05", user.getBirthDate());
        assertEquals("Region2", user.getRegion());
        assertEquals("Commune2", user.getCommune());
        assertEquals("Address2", user.getAddress());

        verify(userRepository).save(user);
    }

    @Test
    void updateUserDataByUsername_UserNotFound() {
        String username = "user1";
        RegisterRequest req = RegisterRequest.builder().build();

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.updateUserDataByUsername(username, req);
        });

        assertEquals("User not found", ex.getMessage());
        verify(userRepository, never()).save(any());
    }
}
