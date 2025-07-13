package com.microservice.inventory.controller;

import com.microservice.inventory.entities.Products;
import com.microservice.inventory.services.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    @Autowired
    IProductsService productsService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Products product) {
        productsService.save(product);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productsService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productsService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        Products existingProduct = productsService.findById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found");
        }
        productsService.deleteById(id);
        return ResponseEntity.ok("Product with ID " + id + " deleted successfully");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> handleNumberFormat(NumberFormatException ex) {
        return ResponseEntity.badRequest().body("ID inválido. Debe ser un número.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error en el servidor: " + ex.getMessage());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @RequestBody Products productDetails) {
        System.out.println("Update request for ID: " + id);
        System.out.println("Payload: " + productDetails);

        Products existingProduct = productsService.findById(id);
        if (existingProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with ID " + id + " not found");
        }

        try {
            existingProduct.setProduct_name(productDetails.getProduct_name());
            existingProduct.setDescription(productDetails.getDescription());
            existingProduct.setType(productDetails.getType());
            existingProduct.setMeasure_unit(productDetails.getMeasure_unit());

            Products updated = productsService.save(existingProduct);
            System.out.println("Updated product: " + updated);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar producto: " + e.getMessage());
        }
    }

}
