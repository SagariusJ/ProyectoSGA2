package com.microservice.inventory.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String warehouse_Name;
    private String direction;
}

