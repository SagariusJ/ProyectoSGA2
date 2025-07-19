package com.microservice.benefits.controller;

import com.microservice.benefits.dto.PatientWithUserDTO;
import com.microservice.benefits.entities.Patients;
import com.microservice.benefits.services.IPatientsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PatientsController {

    @Autowired
    IPatientsService patientsService;

    @PostMapping("/patients/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePatients(@RequestBody Patients patient) {
        patientsService.save(patient);
    }

    @GetMapping("/patients/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(patientsService.findAll());}

    @GetMapping("/patients/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(patientsService.findById(id));
    }

    @DeleteMapping("/patients/delete/{id}")
    public ResponseEntity<?> deletePatients(@PathVariable("id") Long id){
        Patients existingPatients = patientsService.findById(id);
        if(existingPatients==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Benefit with ID " + id + " not found");
        }
        patientsService.deleteById(id);
        return ResponseEntity.ok("Benefit with ID " + id + " deleted succesfully");
    }

    @PutMapping("/patients/update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable("id") Long id, @RequestBody Patients patientdetail) {
        Patients existingPatients = patientsService.findById(id);
        if (existingPatients == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient with ID " + id + " not found");
        }

        existingPatients.setFullName(patientdetail.getFullName());
        existingPatients.setBirthDate(patientdetail.getBirthDate());
        existingPatients.setRegion(patientdetail.getRegion());
        existingPatients.setCommune(patientdetail.getCommune());
        existingPatients.setAddress(patientdetail.getAddress());
        existingPatients.setUserId(patientdetail.getUserId()); // <--- aquí

        patientsService.save(existingPatients);

        return ResponseEntity.ok(existingPatients);
    }


    @GetMapping("/patients/{id}/with-user")
    public ResponseEntity<PatientWithUserDTO> getPatientWithUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(patientsService.getPatientWithUser(id));
    }
}
