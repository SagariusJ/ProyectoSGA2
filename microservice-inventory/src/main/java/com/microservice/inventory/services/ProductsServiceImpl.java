package com.microservice.inventory.services;

import com.microservice.inventory.entities.Products;
import com.microservice.inventory.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductsServiceImpl implements IProductsService {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public Products save(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public Products findById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Products> findAll() {
        return (List<Products>) productsRepository.findAll();
    }
}
