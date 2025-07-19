package com.microservice.benefits.controller;

import com.microservice.benefits.entities.Patient_Benefit;
import com.microservice.benefits.services.IPatient_BenefitService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class Patient_BenefitController {

    @Autowired
    IPatient_BenefitService patbenService;

    @PostMapping("/patben/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePatben(@RequestBody Patient_Benefit patben){patbenService.save(patben);}

    @GetMapping("/patben/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(patbenService.findAll());}

    @GetMapping("/patben/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(patbenService.findById(id));
    }

    @DeleteMapping("/patben/delete/{id}")
    public ResponseEntity<?> deletePatben(@PathVariable("id") Long id){
        Patient_Benefit existingPatients = patbenService.findById(id);
        if(existingPatients==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relation Patient_Benefit with ID " + id + " not found");
        }
        patbenService.deleteById(id);
        return ResponseEntity.ok("Relation Patient_Benefit with ID " + id + " deleted succesfully");
    }
}
