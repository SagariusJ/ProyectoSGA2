package com.microservice.inventory.services;

import com.microservice.inventory.entities.Inventory;

import java.util.List;

public interface IInventoryService {

    Inventory save(Inventory inventario);

    void deleteById(Long id);

    Inventory findById(Long id);

    List<Inventory> findAll();

}
