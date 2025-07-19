package com.microservice.sale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "ventasDetail")
@AllArgsConstructor
@NoArgsConstructor
public class VentaDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta sale;

    @Column
    private Long producto_id;

    @Column(name = "cantidad")
    private int amount;

    @Column(name = "precio")
    private double price;
}
