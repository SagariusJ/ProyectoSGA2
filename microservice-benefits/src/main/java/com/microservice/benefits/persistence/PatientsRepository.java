package com.microservice.benefits.persistence;

import com.microservice.benefits.entities.Patients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends CrudRepository<Patients, Long>{

}
