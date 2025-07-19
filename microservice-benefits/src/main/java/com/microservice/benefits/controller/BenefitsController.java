package com.microservice.benefits.controller;

import com.microservice.benefits.entities.Benefits;
import com.microservice.benefits.services.IBenefitsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/benefits")
public class BenefitsController {
    @Autowired
    IBenefitsService benefitsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBenefits(@RequestBody Benefits benefits){benefitsService.save(benefits);}

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(benefitsService.findAll());}

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(benefitsService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable("id") Long id){
        Benefits existingBenefit = benefitsService.findById(id);
        if(existingBenefit==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Benefit with ID " + id + " not found");
        }
        benefitsService.deleteById(id);
        return ResponseEntity.ok("Benefit with ID " + id + " deleted succesfully");
    }
}
