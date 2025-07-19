package com.microservice.caja.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CajaUpdateRequest {
    private Long usuarioId;
    private LocalDate fechaApertura;
    private LocalDate fechaCierre;
    private Double montoInicial;
    private Double montoFinal;
}
