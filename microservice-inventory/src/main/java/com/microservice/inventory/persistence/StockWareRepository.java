package com.microservice.inventory.persistence;

import com.microservice.inventory.entities.StockWare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StockWareRepository extends CrudRepository<StockWare, Long>{

}
