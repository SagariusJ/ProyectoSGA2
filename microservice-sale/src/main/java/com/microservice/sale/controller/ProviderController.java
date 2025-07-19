package com.microservice.sale.controller;

import com.microservice.sale.entities.Provider;
import com.microservice.sale.services.IProviderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProviderController {

    @Autowired
    IProviderService providerService;

    @PostMapping("/proveedor/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProviders(@RequestBody Provider provider){providerService.save(provider);}

    @GetMapping("/proveedor/all")
    public ResponseEntity<?> findAll(){ return ResponseEntity.ok(providerService.findAll());}

    @GetMapping("/proveedor/search/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(providerService.findById(id));
    }

    @DeleteMapping("/proveedor/delete/{id}")
    public ResponseEntity<?> deleteBenefits(@PathVariable("id") Long id){
        Provider existingProvider = providerService.findById(id);
        if(existingProvider==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Provider with ID " + id + " not found");
        }
        providerService.deleteById(id);
        return ResponseEntity.ok("Provider with ID " + id + " deleted succesfully");
    }
}
