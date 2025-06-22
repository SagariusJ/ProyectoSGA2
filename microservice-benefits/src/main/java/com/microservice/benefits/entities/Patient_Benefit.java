package com.microservice.benefits.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "patient_benefit")
@NoArgsConstructor
@AllArgsConstructor
public class Patient_Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @Column
    private Patients patient;

    @ManyToOne
    @Column
    private Benefits benefit;
}
