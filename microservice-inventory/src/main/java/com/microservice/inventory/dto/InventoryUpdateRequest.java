package com.microservice.inventory.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryUpdateRequest {
    private Long productId;
    private String inventory_number;
    private LocalDate exp_date;
    private Double unit_price;
}
