package com.microservice.benefits.persistence;

import com.microservice.benefits.entities.Patient_Benefit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
public interface Patient_BenefitRepository extends CrudRepository<Patient_Benefit, Long>{
}
