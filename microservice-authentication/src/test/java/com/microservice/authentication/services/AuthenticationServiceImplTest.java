package com.microservice.authentication.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.microservice.authentication.dto.RegisterRequest;
import com.microservice.authentication.entities.User;
import com.microservice.authentication.persistence.UserRepository;
import com.microservice.authentication.security.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

class AuthenticationServiceImplTest {

    @InjectMocks
    private AuthenticationServiceImpl authService;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        String username = "testuser";
        String password = "password";

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("jwt-token");

        String token = authService.login(username, password);

        assertEquals("jwt-token", token);
        verify(authManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil).generateToken(userDetails);
    }

    @Test
    void testRegister_Success() {
        RegisterRequest req = RegisterRequest.builder()
                .username("newuser")
                .password("pass")
                .email("email@example.com")
                .fullName("New User")
                .birthDate("2000-01-01")
                .region("region")
                .commune("commune")
                .address("address")
                .build();

        when(userRepository.findByUsername(req.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.empty());
        when(encoder.encode(req.getPassword())).thenReturn("encoded-pass");

        authService.register(req);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User savedUser = userCaptor.getValue();
        assertEquals("newuser", savedUser.getUsername());
        assertEquals("encoded-pass", savedUser.getPassword());
        assertEquals("email@example.com", savedUser.getEmail());
    }

    @Test
    void testRegister_UsernameExists() {
        RegisterRequest req = RegisterRequest.builder()
                .username("existinguser")
                .email("email@example.com")
                .build();

        when(userRepository.findByUsername(req.getUsername())).thenReturn(Optional.of(new User()));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.register(req));
        assertEquals("Username already exists", ex.getMessage());
    }

    @Test
    void testRegister_EmailExists() {
        RegisterRequest req = RegisterRequest.builder()
                .username("newuser")
                .email("existingemail@example.com")
                .build();

        when(userRepository.findByUsername(req.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(req.getEmail())).thenReturn(Optional.of(new User()));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> authService.register(req));
        assertEquals("Email already exists", ex.getMessage());
    }
}
