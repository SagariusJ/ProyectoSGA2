package com.microservice.benefits.services;

import com.microservice.benefits.entities.Benefits;
import com.microservice.benefits.persistence.BenefitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BenefitsServiceImpl implements IBenefitsService{

    @Autowired
    BenefitsRepository benefitsRepository;

    @Override
    public Benefits save(Benefits benefit) {return benefitsRepository.save(benefit);}

    @Override
    public void deleteById(Long id) {benefitsRepository.deleteById(id);}

    @Override
    public Benefits findById(Long id) {return benefitsRepository.findById(id).orElse(null);}

    @Override
    public List<Benefits> findAll() {return (List<Benefits>) benefitsRepository.findAll();}
}
