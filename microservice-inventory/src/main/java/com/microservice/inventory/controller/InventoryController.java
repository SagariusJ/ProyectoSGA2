package com.microservice.inventory.controller;

import com.microservice.inventory.dto.InventoryUpdateRequest;
import com.microservice.inventory.entities.Inventory;
import com.microservice.inventory.entities.Products;
import com.microservice.inventory.services.IInventoryService;
import com.microservice.inventory.services.IProductsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    IInventoryService inventoryService;

    @Autowired
    private IProductsService productService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveInventory(@RequestBody Inventory inventory) {
        inventoryService.save(inventory);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(inventoryService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteInventory(@PathVariable("id") Long id) {
        Inventory existingInventory = inventoryService.findById(id);
        if (existingInventory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory with ID " + id + " not found");
        }

        inventoryService.deleteById(id);
        return ResponseEntity.ok("Inventory with ID " + id + " deleted successfully");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateInventory(@PathVariable("id") Long id, @RequestBody InventoryUpdateRequest inventoryDetails) {
        Inventory existingInventory = inventoryService.findById(id);
        if (existingInventory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inventory with ID " + id + " not found");
        }

        // Buscar el producto por ID
        Products product = productService.findById(inventoryDetails.getProductId());
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + inventoryDetails.getProductId() + " not found");
        }

        // Actualizar los datos
        existingInventory.setProduct(product);
        existingInventory.setInventory_number(inventoryDetails.getInventory_number());
        existingInventory.setExp_date(inventoryDetails.getExp_date());
        existingInventory.setUnit_price(inventoryDetails.getUnit_price());

        inventoryService.save(existingInventory);

        return ResponseEntity.ok(existingInventory);
    }

}