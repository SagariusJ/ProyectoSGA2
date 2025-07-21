package com.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                // Deshabilita CSRF porque es REST + JWT
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                // 1) Activa CORS (tu CorsWebFilter lo manejará)
                .cors(cors -> {})

                // 2) Controla primero los preflight OPTIONS
                .authorizeExchange(ex -> ex
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()

                        // 3) Permite rutas públicas
                        .pathMatchers("/api/auth/**").permitAll()
                        .pathMatchers("/api/inventory/all").permitAll()
                        .pathMatchers("/api/inventory/search/**").permitAll()
                        .pathMatchers("/api/products/all").permitAll()
                        .pathMatchers("/api/products/search/**").permitAll()
                        .pathMatchers("/api/stockware/all").permitAll()
                        .pathMatchers("/api/stockware/search/**").permitAll()
                        .pathMatchers("/api/warehouse/all").permitAll()
                        .pathMatchers("/api/warehouse/search/**").permitAll()
                        .pathMatchers("/api/benefits/**").permitAll()
                        .pathMatchers("/api/patben/**").permitAll()
                        .pathMatchers("/api/patients/**").permitAll()
                        .pathMatchers("/api/compra/**").permitAll()
                        .pathMatchers("/api/venta/**").permitAll()
                        .pathMatchers("/api/ventadet/**").permitAll()
                        .pathMatchers("/api/proveedor/**").permitAll()
                        .pathMatchers("/api/compradet/**").permitAll()
                        .pathMatchers("/api/caja/**").permitAll()
                        .pathMatchers("/api/fraccionamiento/**").permitAll()
                        .pathMatchers("/api/informe/**").permitAll()
                        // 4) El resto requiere JWT válido
                        .anyExchange().authenticated()
                )

                // 5) Recurso server JWT con tu decoder
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtDecoder(jwtDecoder()))
                )
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(
                "dCTT3lnkifqPWXd0OqorNhnpcdBc4yy8WrMMbYko52k=".getBytes(),
                "HmacSHA256"
        );
        return NimbusReactiveJwtDecoder.withSecretKey(secretKey).build();
    }
}
