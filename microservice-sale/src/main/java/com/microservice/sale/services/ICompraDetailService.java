package com.microservice.sale.services;

import com.microservice.sale.entities.CompraDetail;

import java.util.List;

public interface ICompraDetailService {

    CompraDetail save(CompraDetail compraDetail);

    void deleteById(Long id);

    CompraDetail findById(Long id);

    List<CompraDetail> findAll();
}
