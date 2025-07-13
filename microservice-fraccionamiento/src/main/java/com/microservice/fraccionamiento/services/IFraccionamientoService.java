package com.microservice.fraccionamiento.services;

import com.microservice.fraccionamiento.entities.Fraccionamiento;

import java.util.List;

public interface IFraccionamientoService {

    Fraccionamiento save(Fraccionamiento frac);

    void deleteById(Long id);

    Fraccionamiento findById(Long id);

    List<Fraccionamiento> findAll();
}
