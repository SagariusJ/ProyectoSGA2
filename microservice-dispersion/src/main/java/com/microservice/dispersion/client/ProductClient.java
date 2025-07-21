package com.microservice.dispersion.client;

import com.microservice.dispersion.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service", url = "https://microservice-products-production.up.railway.app") // cambia el URL si usas Eureka
public interface ProductClient {
    @GetMapping("/api/products/search/{id}")
    ProductsDTO getProductById(@PathVariable("id") Long id);
}
