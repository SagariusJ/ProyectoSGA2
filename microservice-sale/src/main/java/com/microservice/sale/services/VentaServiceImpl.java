package com.microservice.sale.services;

import com.microservice.sale.entities.Venta;
import com.microservice.sale.persistence.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements IVentaService{

    @Autowired
    VentaRepository ventaRepository;
    @Override
    public Venta save(Venta sale) {
        return ventaRepository.save(sale);
    }

    @Override
    public void deleteById(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public Venta findById(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Venta> findAll() {
        return (List<Venta>) ventaRepository.findAll();
    }
}
