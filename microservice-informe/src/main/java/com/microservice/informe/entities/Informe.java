package com.microservice.informe.entities;

import jakarta.persistence.*;
import lombok.Data;
import com.microservice.informe.dto.CompraDTO;
import com.microservice.informe.dto.VentaDTO;

import java.time.LocalDate;
import java.util.List;

@Data
public class Informe {
    private int totalCompras;
    private double montoCompras;
    private int totalVentas;
    private double montoVentas;
    private double balance;
    private LocalDate fechaInforme;
    private List<CompraDTO> compras;
    private List<VentaDTO> ventas;
}
