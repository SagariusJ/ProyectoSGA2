package com.microservice.dispersion.controller;

import com.microservice.dispersion.entities.Dispersion;
import com.microservice.dispersion.services.IDispersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispersion")
public class DispersionController {

    @Autowired
    IDispersionService dispersionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDispersion(@RequestBody Dispersion disp){dispersionService.save(disp);}

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){return ResponseEntity.ok(dispersionService.findAll());}

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(dispersionService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDispersion(@PathVariable("id") Long id){
        Dispersion existingDisp = dispersionService.findById(id);
        if (existingDisp == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delivery with ID " + id + " not found");
        }
        dispersionService.deleteById(id);
        return ResponseEntity.ok("Delivery with ID " + id + " deleted succesfully");
    }
}
