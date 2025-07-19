package com.microservice.sale.controller;

import com.microservice.sale.entities.Compra;
import com.microservice.sale.services.ICompraService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CompraController {

    @Autowired
    ICompraService compraService;

    @PostMapping("/compra/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCompra(@RequestBody Compra compra){compraService.save(compra);}

    @GetMapping("/compra/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(compraService.findAll());}

    @GetMapping("/compra/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(compraService.findById(id));
    }

    @DeleteMapping("/compra/delete/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable("id") Long id){
        Compra existingCompra = compraService.findById(id);
        if(existingCompra==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra with ID " + id + " not found");
        }
        compraService.deleteById(id);
        return ResponseEntity.ok("Compra with ID " + id + " deleted succesfully");
    }
}
