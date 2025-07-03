package com.microservice.sale.services;

import com.microservice.sale.entities.VentaDetail;

import java.util.List;

public interface IVentaDetailService {

    VentaDetail save(VentaDetail ventaDetail);

    void deleteById(Long id);

    VentaDetail findById(Long id);

    List<VentaDetail> findAll();
}
