package com.microservice.inventory.controller;

import com.microservice.inventory.entities.StockWare;
import com.microservice.inventory.services.IStockWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stockware")
public class StockWareController {

    @Autowired
    IStockWareService stockWareService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStockWare(@RequestBody StockWare stockWare) {
        stockWareService.save(stockWare);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(stockWareService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(stockWareService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStockWare(@PathVariable("id") Long id) {
        StockWare existingStockWare = stockWareService.findById(id);
        if (existingStockWare == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("StockWare with ID " + id + " not found");
        }
        stockWareService.deleteById(id);
        return ResponseEntity.ok("StockWare with ID " + id + " deleted successfully");
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStockWare(@PathVariable("id") Long id, @RequestBody StockWare stockWareDetails) {
        StockWare existingStockWare = stockWareService.findById(id);
        if (existingStockWare == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("StockWare with ID " + id + " not found");
        }

        existingStockWare.setWarehouse(stockWareDetails.getWarehouse());
        existingStockWare.setLot(stockWareDetails.getLot());
        existingStockWare.setAmount(stockWareDetails.getAmount());

        stockWareService.save(existingStockWare);

        return ResponseEntity.ok(existingStockWare);
    }
}
