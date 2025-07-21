package com.microservice.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Orígenes permitidos — aquí colocas el URL de tu frontend Angular
        corsConfig.addAllowedOrigin("https://proyectosgafront-production.up.railway.app");

        // Métodos permitidos (GET, POST, PUT, DELETE, etc.)
        corsConfig.addAllowedMethod("*");

        // Headers permitidos
        corsConfig.addAllowedHeader("*");

        // Permitir credenciales (si usarás cookies o Authorization headers)
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
