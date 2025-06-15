package com.microservice.inventory.services;

import com.microservice.inventory.entities.Warehouse;
import java.util.List;

public interface IWarehouseService {
    Warehouse save(Warehouse warehouse);
    void deleteById(Long id);
    Warehouse findById(Long id);
    List<Warehouse> findAll();
}
