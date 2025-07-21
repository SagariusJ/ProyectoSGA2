package com.microservice.informe.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class VentaDTO {
    private long id;
    private LocalDate saleDate;
    private double cost;
    private List<VentaDetailDTO> details;
}
