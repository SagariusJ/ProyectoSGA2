package com.microservice.informe.dto;

import com.microservice.sale.entities.CompraDetail;
import com.microservice.sale.entities.Provider;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CompraDTO {
    private Long id;
    private Provider provider;
    private LocalDate fecha;
    private double total;
    private List<CompraDetail> details;
}
