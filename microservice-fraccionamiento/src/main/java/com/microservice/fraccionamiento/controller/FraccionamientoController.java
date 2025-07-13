package com.microservice.fraccionamiento.controller;

import com.microservice.fraccionamiento.entities.Fraccionamiento;
import com.microservice.fraccionamiento.services.IFraccionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraccionamiento")
public class FraccionamientoController {

    @Autowired
    IFraccionamientoService fraccionamientoService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveFraccionamiento(Fraccionamiento fraccionamiento){fraccionamientoService.save(fraccionamiento);}

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){return ResponseEntity.ok(fraccionamientoService.findAll());}

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(fraccionamientoService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFraccionamiento(@PathVariable("id") Long id){
        Fraccionamiento existingFrac = fraccionamientoService.findById(id);
        if(existingFrac == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fraction with ID " + id + " not found");
        }
        fraccionamientoService.deleteById(id);
        return ResponseEntity.ok("Fraction with ID " + id + " deleted succesfully");
    }

}
