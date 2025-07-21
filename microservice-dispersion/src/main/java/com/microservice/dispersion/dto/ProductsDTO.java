package com.microservice.dispersion.dto;

import lombok.Data;

@Data
public class ProductsDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    // otros campos que tenga tu producto
}