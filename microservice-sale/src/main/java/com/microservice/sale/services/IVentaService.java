package com.microservice.sale.services;

import com.microservice.sale.entities.Venta;

import java.util.List;

public interface IVentaService {

    Venta save(Venta sale);

    void deleteById(Long id);

    Venta findById(Long id);

    List<Venta> findAll();
}
