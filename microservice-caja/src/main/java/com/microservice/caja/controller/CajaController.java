package com.microservice.caja.controller;

import com.microservice.caja.entities.Caja;
import com.microservice.caja.services.ICajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/caja")
public class CajaController {

    @Autowired
    ICajaService cajaService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCaja(Caja caja){cajaService.save(caja);}

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){return ResponseEntity.ok(cajaService.findAll());}

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(cajaService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCaja(@PathVariable("id") Long id){
        Caja existingBox = cajaService.findById(id);
        if (existingBox == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Box with ID " + id + " not found");
        }
        cajaService.deleteById(id);
        return ResponseEntity.ok("Box with ID " + id + " deleted succesfully");
    }
}
