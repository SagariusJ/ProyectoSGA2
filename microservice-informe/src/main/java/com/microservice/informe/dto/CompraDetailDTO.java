package com.microservice.informe.dto;

import lombok.Data;

@Data
public class CompraDetailDTO {
    private Long id;
    private int cantidad;
    private double precio;
}
