package com.microservice.caja.persistence;

import com.microservice.caja.entities.Caja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends CrudRepository<Caja, Long>{
}
