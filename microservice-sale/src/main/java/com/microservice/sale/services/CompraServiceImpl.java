package com.microservice.sale.services;

import com.microservice.sale.entities.Compra;
import com.microservice.sale.persistence.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServiceImpl implements ICompraService{

    @Autowired
    CompraRepository compraRepository;

    @Override
    public Compra save(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public void deleteById(Long id) {
        compraRepository.deleteById(id);
    }

    @Override
    public Compra findById(Long id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public List<Compra> findAll() {
        return (List<Compra>) compraRepository.findAll();
    }
}
