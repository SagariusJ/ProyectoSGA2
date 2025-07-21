package com.microservice.dispersion.client;

import com.microservice.dispersion.dto.PatientsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patients-service", url = "https://microservice-benefits-production.up.railway.app") // cambia el URL si usas Eureka
public interface PatientClient {
    @GetMapping("/api/patients/search/{id}")
    PatientsDTO getPatientById(@PathVariable("id") Long id);
}