package com.microservice.informe.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CompraDTO {
    private Long id;
    private LocalDate fecha;
    private double total;
    private List<CompraDetailDTO> details;
}
