package com.microservice.fraccionamiento.client;

import com.microservice.fraccionamiento.dto.ProductDTO;
import com.microservice.fraccionamiento.dto.WarehouseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-inventory", url = "https://microservice-inventory-production.up.railway.app")
public interface InventoryClient {

    @GetMapping("/api/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);


    @GetMapping("/api/warehouse/{id}")
    WarehouseDTO getWarehouseById(@PathVariable("id") Long id);
}
