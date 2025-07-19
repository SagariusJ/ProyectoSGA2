package com.microservice.dispersion.persistence;

import com.microservice.dispersion.entities.Dispersion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispersionRepository extends CrudRepository<Dispersion, Long>{
}
