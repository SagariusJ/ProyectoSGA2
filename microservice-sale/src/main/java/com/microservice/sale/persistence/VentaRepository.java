package com.microservice.sale.persistence;

import com.microservice.sale.entities.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long>{

}
