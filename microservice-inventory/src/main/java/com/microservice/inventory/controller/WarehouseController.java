package com.microservice.inventory.controller;

import com.microservice.inventory.entities.Warehouse;
import com.microservice.inventory.services.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    IWarehouseService warehouseService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveWarehouse(@RequestBody Warehouse warehouse) {
        warehouseService.save(warehouse);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable("id") Long id) {
        Warehouse existingWarehouse = warehouseService.findById(id);
        if (existingWarehouse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse with ID " + id + " not found");
        }
        warehouseService.deleteById(id);
        return ResponseEntity.ok("Warehouse with ID " + id + " deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable("id") Long id, @RequestBody Warehouse warehouseDetails) {
        Warehouse existingWarehouse = warehouseService.findById(id);
        if (existingWarehouse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warehouse with ID " + id + " not found");
        }

        existingWarehouse.setWarehouse_Name(warehouseDetails.getWarehouse_Name());
        existingWarehouse.setDirection(warehouseDetails.getDirection());

        warehouseService.save(existingWarehouse);

        return ResponseEntity.ok(existingWarehouse);
    }
}
