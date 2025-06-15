package com.microservice.inventory.services;

import com.microservice.inventory.entities.StockWare;
import java.util.List;

public interface IStockWareService {
    StockWare save(StockWare stockWare);
    void deleteById(Long id);
    StockWare findById(Long id);
    List<StockWare> findAll();
}
