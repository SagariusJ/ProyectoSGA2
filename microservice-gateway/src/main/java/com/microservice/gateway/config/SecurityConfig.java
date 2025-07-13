package com.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.*;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

import javax.crypto.spec.SecretKeySpec;

@EnableWebFluxSecurity
public class SecurityConfig {

    // CHAIN 1 → Rutas públicas (sin JWT)
    @Bean
    @Order(1)
    public SecurityWebFilterChain publicSecurityChain(ServerHttpSecurity http) {
        return http
                .securityMatcher(ServerWebExchangeMatchers.pathMatchers(
                        "/api/auth/**",
                        "/api/inventory/all",
                        "/api/inventory/search/**",
                        "/api/products/all",
                        "/api/products/search/**",
                        "/api/stockware/all",
                        "/api/stockware/search/**",
                        "/api/warehouse/all",
                        "/api/warehouse/search/**"
                ))
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(ex -> ex
                        .anyExchange().permitAll()
                )
                .build();
    }

    // CHAIN 2 → Todo lo demás requiere JWT
    @Bean
    @Order(2)
    public SecurityWebFilterChain securedSecurityChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(ex -> ex
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
                )
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec("mySecretKey".getBytes(), "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }
}
