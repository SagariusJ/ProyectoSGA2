package com.microservice.fraccionamiento.persistence;

import com.microservice.fraccionamiento.entities.Fraccionamiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraccionamientoRepository extends CrudRepository<Fraccionamiento, Long> {
}
