package com.microservice.inventory.persistence;

import com.microservice.inventory.entities.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long>{

}
