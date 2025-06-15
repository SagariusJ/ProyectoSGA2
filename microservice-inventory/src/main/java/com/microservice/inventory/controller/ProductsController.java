package com.microservice.inventory.controller;

import com.microservice.inventory.entities.Products;
import com.microservice.inventory.services.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    IProductsService productsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Products product) {
        productsService.save(product);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productsService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Products existingProduct = productsService.findById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found");
        }
        productsService.deleteById(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Products productDetails) {
        Products existingProduct = productsService.findById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found");
        }

        existingProduct.setProduct_name(productDetails.getProduct_name());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setType(productDetails.getType());
        existingProduct.setMeasure_unit(productDetails.getMeasure_unit());

        productsService.save(existingProduct);

        return ResponseEntity.ok(existingProduct);
    }
}
