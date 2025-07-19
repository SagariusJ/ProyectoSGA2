package com.microservice.sale.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "compraDetail")
@NoArgsConstructor
@AllArgsConstructor
public class CompraDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    @JsonBackReference
    private Compra compra;

    @Column
    private Long productoId;

    @Column
    private String inventory_number;

    @Column
    private int amount;

    @Column
    private double precio;
}
