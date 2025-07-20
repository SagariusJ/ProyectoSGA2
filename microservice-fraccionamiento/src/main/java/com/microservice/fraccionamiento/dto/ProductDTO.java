package com.microservice.fraccionamiento.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String product_name;
    private String description;
    private String type;
    private String measure_unit;
}
