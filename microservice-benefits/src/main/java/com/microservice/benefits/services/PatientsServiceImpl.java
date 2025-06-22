package com.microservice.benefits.services;

import com.microservice.benefits.entities.Patients;
import com.microservice.benefits.persistence.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientsServiceImpl implements IPatientsService{

    @Autowired
    PatientsRepository patientsRepository;

    @Override
    public Patients save(Patients patient) {return patientsRepository.save(patient);}

    @Override
    public void deleteById(Long id) {patientsRepository.deleteById(id);}

    @Override
    public Patients findById(Long id) {return patientsRepository.findById(id).orElse(null);}

    @Override
    public List<Patients> findAll() {return (List<Patients>) patientsRepository.findAll();}
}
