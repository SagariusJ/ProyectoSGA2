package com.microservice.benefits.services;

import com.microservice.benefits.entities.Patient_Benefit;
import com.microservice.benefits.persistence.Patient_BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Patient_BenefitImpl implements IPatient_BenefitService{

    @Autowired
    Patient_BenefitRepository patient_benefitRepository;

    @Override
    public Patient_Benefit save(Patient_Benefit patben) {
        return patient_benefitRepository.save(patben);
    }

    @Override
    public void deleteById(Long id) {
        patient_benefitRepository.deleteById(id);
    }

    @Override
    public Patient_Benefit findById(Long id) {
        return patient_benefitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient_Benefit> findAll() {
        return (List<Patient_Benefit>) patient_benefitRepository.findAll();
    }
}
