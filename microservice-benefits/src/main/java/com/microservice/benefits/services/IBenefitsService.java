package com.microservice.benefits.services;

import com.microservice.benefits.entities.Benefits;

import java.util.List;

public interface IBenefitsService {

    Benefits save(Benefits benefit);

    void deleteById(Long id);

    Benefits findById(Long id);

    List<Benefits> findAll();
}
