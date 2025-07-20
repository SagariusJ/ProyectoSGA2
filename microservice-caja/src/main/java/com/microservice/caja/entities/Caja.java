package com.microservice.caja.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "caja")
@AllArgsConstructor
@NoArgsConstructor
public class Caja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "apertura")
    private LocalDate fechaApertura;

    @Column(name = "cierre")
    private LocalDate fechaCierre;

    @Column(name = "montoApertura")
    private Double montoInicial;

    @Column(name = "montoCierre")
    private Double montoFinal;
}
