package com.microservice.sale.controller;

import com.microservice.sale.entities.Venta;
import com.microservice.sale.entities.VentaDetail;
import com.microservice.sale.services.IVentaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VentaController {

    @Autowired
    IVentaService ventaService;

    @PostMapping("/venta/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveVenta(@RequestBody Venta venta) {
        // Asignar la referencia "sale" a cada detalle para evitar problemas JPA
        if (venta.getDetails() != null) {
            for (VentaDetail detail : venta.getDetails()) {
                detail.setSale(venta);
            }
        }

        Venta savedVenta = ventaService.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVenta);
    }


    @GetMapping("/venta/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(ventaService.findAll());}

    @GetMapping("/venta/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ventaService.findById(id));
    }

    @DeleteMapping("/venta/delete/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable("id") Long id){
        Venta existingVenta = ventaService.findById(id);
        if(ventaService==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta with ID " + id + " not found");
        }
        ventaService.deleteById(id);
        return ResponseEntity.ok("Venta with ID " + id + " deleted succesfully");
    }
}
