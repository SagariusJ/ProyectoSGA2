package com.microservice.dispersion.services;

import com.microservice.dispersion.entities.Dispersion;

import java.util.List;

public interface IDispersionService {
    Dispersion save(Dispersion disp);

    void deleteById(Long id);

    Dispersion findById(Long id);

    List<Dispersion> findAll();
}
