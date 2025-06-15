package com.microservice.inventory.services;

import com.microservice.inventory.entities.Inventory;
import com.microservice.inventory.persistence.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements IInventoryService{

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public Inventory save(Inventory inventario) {
        return inventoryRepository.save(inventario);
    }

    @Override
    public void deleteById(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public Inventory findById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Inventory> findAll() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

}
