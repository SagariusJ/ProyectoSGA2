package com.microservice.benefits.services;

import com.microservice.benefits.entities.Patient_Benefit;

import java.util.List;

public interface IPatient_BenefitService {

    Patient_Benefit save(Patient_Benefit patben);

    void deleteById(Long id);

    Patient_Benefit findById(Long id);

    List<Patient_Benefit> findAll();
}
