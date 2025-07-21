package com.microservice.informe.dto;

import lombok.Data;

@Data
public class VentaDetailDTO {
    private Long id;
    private int cantidad;
    private double precio;
    // Agrega otros campos si necesitas
}
