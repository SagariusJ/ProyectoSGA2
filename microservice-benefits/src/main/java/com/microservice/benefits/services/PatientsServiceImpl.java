package com.microservice.benefits.services;

import com.microservice.benefits.client.UserClient;
import com.microservice.benefits.dto.PatientWithUserDTO;
import com.microservice.benefits.dto.UserDTO;
import com.microservice.benefits.entities.Patients;
import com.microservice.benefits.persistence.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientsServiceImpl implements IPatientsService{

    @Autowired
    PatientsRepository patientsRepository;
    @Autowired
    private UserClient userClient;

    @Override
    public Patients save(Patients patient) {return patientsRepository.save(patient);}

    @Override
    public void deleteById(Long id) {patientsRepository.deleteById(id);}

    @Override
    public Patients findById(Long id) {return patientsRepository.findById(id).orElse(null);}

    @Override
    public List<Patients> findAll() {return (List<Patients>) patientsRepository.findAll();}

    @Override
    public PatientWithUserDTO getPatientWithUser(Long patientId) {
        Patients patient = patientsRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        UserDTO user = userClient.getUserById(patient.getId());

        return new PatientWithUserDTO(patient, user);
    }
}
