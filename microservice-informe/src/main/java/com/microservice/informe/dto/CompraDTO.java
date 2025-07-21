package com.microservice.informe.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CompraDTO {
    private Long id;
    private ProviderDTO provider;
    private LocalDate fecha;
    private double total;
    private List<CompraDetailDTO> details;
}
