package com.microservice.sale.services;

import com.microservice.sale.entities.Compra;

import java.util.List;
public interface ICompraService {
    Compra save(Compra compra);

    void deleteById(Long id);

    Compra findById(Long id);

    List<Compra> findAll();
}
