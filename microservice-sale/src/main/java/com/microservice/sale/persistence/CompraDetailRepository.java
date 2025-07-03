package com.microservice.sale.persistence;

import com.microservice.sale.entities.CompraDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDetailRepository extends CrudRepository<CompraDetail, Long>{
}
