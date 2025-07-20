package com.microservice.gateway.config;


import com.sun.tools.javac.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.server.WebFilter;

@Configuration
public class CorsGlobalConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // Orígenes permitidos — aquí colocas el URL de tu frontend Angular
        corsConfig.setAllowedOrigins(List.of("https://proyectosgafront-production.up.railway.app"));

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
    @Bean
    public WebFilter corsHeadersFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            response.getHeaders().add("Access-Control-Allow-Origin", "https://proyectosgafront-production.up.railway.app");
            response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.getHeaders().add("Access-Control-Allow-Headers", "Authorization, Content-Type");
            response.getHeaders().add("Access-Control-Allow-Credentials", "true");

            return chain.filter(exchange);
        };
    }

}
