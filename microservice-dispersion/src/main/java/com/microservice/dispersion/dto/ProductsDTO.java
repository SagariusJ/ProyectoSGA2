package com.microservice.dispersion.dto;

import lombok.Data;

@Data
public class ProductsDTO {
    private Long id;
    private String product_name;
    private String description;
}