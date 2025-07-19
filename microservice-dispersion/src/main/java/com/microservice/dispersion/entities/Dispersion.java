package com.microservice.dispersion.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "Dispersion")
@AllArgsConstructor
@NoArgsConstructor

public class Dispersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "producto")
    private Long productoId;

    @Column(name = "paciente")
    private Long pacienteId;

    @Column(name = "amount")
    private Integer cantidad;

    private LocalDate fecha;
}
