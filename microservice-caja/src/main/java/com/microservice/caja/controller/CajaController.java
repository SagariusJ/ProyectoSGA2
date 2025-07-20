package com.microservice.caja.controller;

import com.microservice.caja.dto.CajaUpdateRequest;
import com.microservice.caja.entities.Caja;
import com.microservice.caja.services.ICajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CajaController {

    @Autowired
    ICajaService cajaService;

    @PostMapping("/caja/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCaja(@RequestBody Caja caja){cajaService.save(caja);}

    @GetMapping("/caja/all")
    public ResponseEntity<?> findAll(){return ResponseEntity.ok(cajaService.findAll());}

    @GetMapping("/caja/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(cajaService.findById(id));
    }

    @DeleteMapping("/caja/delete/{id}")
    public ResponseEntity<?> deleteCaja(@PathVariable("id") Long id){
        Caja existingBox = cajaService.findById(id);
        if (existingBox == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Box with ID " + id + " not found");
        }
        cajaService.deleteById(id);
        return ResponseEntity.ok("Box with ID " + id + " deleted succesfully");
    }

    @PutMapping("/caja/update/{id}")
    public ResponseEntity<?> updateCaja(@PathVariable("id") Long id, @RequestBody CajaUpdateRequest cajaDetails){
        Caja existingBox = cajaService.findById(id);
        if(existingBox == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Box with ID " + id + " not found");
        }

        existingBox.setFechaApertura(cajaDetails.getFechaApertura());
        existingBox.setFechaCierre(cajaDetails.getFechaCierre());
        existingBox.setMontoInicial(cajaDetails.getMontoInicial());
        existingBox.setMontoFinal(cajaDetails.getMontoFinal());
        existingBox.setUsuarioId(cajaDetails.getUsuarioId());

        cajaService.save(existingBox);

        return ResponseEntity.ok(existingBox);
    }
}
