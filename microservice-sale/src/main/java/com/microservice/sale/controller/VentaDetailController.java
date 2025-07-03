package com.microservice.sale.controller;

import com.microservice.sale.entities.VentaDetail;
import com.microservice.sale.services.IVentaDetailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventadet")
public class VentaDetailController {

    @Autowired
    IVentaDetailService ventaDetailService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDetails(@RequestBody VentaDetail ventaDetail){ventaDetailService.save(ventaDetail);}

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(ventaDetailService.findAll());}

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ventaDetailService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable("id") Long id){
        VentaDetail existingDetails = ventaDetailService.findById(id);
        if(ventaDetailService==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Details with ID " + id + " not found");
        }
        ventaDetailService.deleteById(id);
        return ResponseEntity.ok("Details with ID " + id + " deleted succesfully");
    }
}
