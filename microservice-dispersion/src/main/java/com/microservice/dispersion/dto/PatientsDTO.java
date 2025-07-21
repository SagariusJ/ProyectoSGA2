package com.microservice.dispersion.dto;

import lombok.Data;

@Data
public class PatientsDTO {
    private Long id;
    private String nombre;
    private String rut;
    // otros campos que tenga tu paciente
}
