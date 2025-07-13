package com.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                // 🔒 Deshabilita CSRF (para REST + JWT es lo recomendado)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // 🔐 Configura rutas permitidas y autenticadas
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/auth/**").permitAll()
                        .pathMatchers("/api/inventory/all").permitAll()
                        .pathMatchers("/api/inventory/search/**").permitAll()
                        .pathMatchers("/api/products/all").permitAll()
                        .pathMatchers("/api/products/search/**").permitAll()
                        .pathMatchers("/api/stockware/all").permitAll()
                        .pathMatchers("/api/stockware/search/**").permitAll()
                        .pathMatchers("/api/warehouse/all").permitAll()
                        .pathMatchers("/api/warehouse/search/**").permitAll()
                        .anyExchange().authenticated() // todas las demás requieren JWT
                )

                // 🪪 Configura decodificación JWT
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtDecoder(jwtDecoder())
                        )
                )
                .build();
    }

    // 🔐 Usa misma clave que en authentication para validar los JWT
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec("Q1tDqYIfqGxE96C4lZ3E0mDGrnpb4tR8Z0Gf1nFaJ4s=".getBytes(), "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }
}
