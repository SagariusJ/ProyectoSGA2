package com.microservice.caja.services;

import com.microservice.caja.entities.Caja;

import java.util.List;
public interface ICajaService {

    Caja save(Caja box);

    void deleteById(Long id);

    Caja findById(Long id);

    List<Caja> findAll();
}
