package com.microservice.inventory.services;

import com.microservice.inventory.entities.StockWare;
import com.microservice.inventory.persistence.StockWareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockWareServiceImpl implements IStockWareService {

    @Autowired
    StockWareRepository stockWareRepository;

    @Override
    public StockWare save(StockWare stockWare) {
        return stockWareRepository.save(stockWare);
    }

    @Override
    public void deleteById(Long id) {
        stockWareRepository.deleteById(id);
    }

    @Override
    public StockWare findById(Long id) {
        return stockWareRepository.findById(id).orElse(null);
    }

    @Override
    public List<StockWare> findAll() {
        return (List<StockWare>) stockWareRepository.findAll();
    }
}
