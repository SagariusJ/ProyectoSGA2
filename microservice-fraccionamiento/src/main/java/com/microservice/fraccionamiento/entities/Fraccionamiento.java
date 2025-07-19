package com.microservice.fraccionamiento.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "Fraccionamiento")
@AllArgsConstructor
@NoArgsConstructor
public class Fraccionamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "producto_origen")
    private Long productoId;

    @Column(name = "amount")
    private Integer cantidad;

    @Column(name = "sucursal_origen")
    private Long sucursalOrigenId;

    @Column(name = "sucursal_destino")
    private Long sucursalDestinoId;

    private LocalDate fecha;

    @Column(name = "usuario")
    private Long usuarioId;
}
