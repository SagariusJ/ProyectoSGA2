package com.microservice.benefits.services;

import com.microservice.benefits.dto.PatientWithUserDTO;
import com.microservice.benefits.entities.Patients;
import java.util.List;

public interface IPatientsService {

    Patients save(Patients patient);

    void deleteById(Long id);

    Patients findById(Long id);

    List<Patients> findAll();

    PatientWithUserDTO getPatientWithUser(Long patientId);
}
