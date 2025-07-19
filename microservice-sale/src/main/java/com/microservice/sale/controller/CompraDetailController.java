package com.microservice.sale.controller;

import com.microservice.sale.entities.CompraDetail;
import com.microservice.sale.services.ICompraDetailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compradet")
public class CompraDetailController {

    @Autowired
    ICompraDetailService compraDetailService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCompraDetail(@RequestBody CompraDetail compraDetail){compraDetailService.save(compraDetail);}

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(compraDetailService.findAll());}

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(compraDetailService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable("id") Long id){
        CompraDetail existingDetails = compraDetailService.findById(id);
        if(existingDetails==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Details with ID " + id + " not found");
        }
        compraDetailService.deleteById(id);
        return ResponseEntity.ok("Details with ID " + id + " deleted succesfully");
    }
}
