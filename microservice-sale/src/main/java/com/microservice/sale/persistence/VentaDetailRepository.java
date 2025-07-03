package com.microservice.sale.persistence;

import com.microservice.sale.entities.VentaDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaDetailRepository extends CrudRepository<VentaDetail, Long>{
}
