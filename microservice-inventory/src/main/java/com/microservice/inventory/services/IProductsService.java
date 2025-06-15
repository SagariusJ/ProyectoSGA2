package com.microservice.inventory.services;

import com.microservice.inventory.entities.Products;
import java.util.List;

public interface IProductsService {
    Products save(Products product);
    void deleteById(Long id);
    Products findById(Long id);
    List<Products> findAll();
}

