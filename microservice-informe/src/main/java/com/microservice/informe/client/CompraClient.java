package com.microservice.informe.client;

import com.microservice.informe.dto.CompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name="compra-service", url="https://microservice-sale-production.up.railway.app")
public interface CompraClient {
    @GetMapping("/api/compra/all")
    List<CompraDTO> getAll();
}
