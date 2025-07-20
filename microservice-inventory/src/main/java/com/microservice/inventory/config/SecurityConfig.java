package com.microservice.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Permite GET públicos sin token
                        .requestMatchers("/api/inventory/all", "/api/inventory/search/**").permitAll()
                        .requestMatchers("/api/products/all", "/api/products/search/**").permitAll()
                        .requestMatchers("/api/stockware/all", "/api/stockware/search/**").permitAll()
                        .requestMatchers("/api/warehouse/all", "/api/warehouse/search/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        // Para cualquier otro endpoint, se requiere autenticación
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> {}) // Habilita CORS con la configuración del bean corsConfigurationSource
                .build();
    }
}
