package com.microservice.informe.client;

import com.microservice.informe.dto.VentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name="venta-service", url="https://microservice-sale-production.up.railway.app")
public interface VentaClient {
    @GetMapping("/api/venta/all")
    List<VentaDTO> getAll();
}
