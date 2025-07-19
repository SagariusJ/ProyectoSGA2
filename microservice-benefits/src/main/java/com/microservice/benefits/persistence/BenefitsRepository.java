package com.microservice.benefits.persistence;

import com.microservice.benefits.entities.Benefits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
public interface BenefitsRepository extends CrudRepository<Benefits, Long> {
}
