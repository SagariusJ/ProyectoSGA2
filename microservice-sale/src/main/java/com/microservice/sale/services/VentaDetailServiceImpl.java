package com.microservice.sale.services;

import com.microservice.sale.entities.VentaDetail;
import com.microservice.sale.persistence.VentaDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaDetailServiceImpl implements IVentaDetailService{

    @Autowired
    VentaDetailRepository ventaDetailRepository;

    @Override
    public VentaDetail save(VentaDetail ventaDetail) {return ventaDetailRepository.save(ventaDetail);}

    @Override
    public void deleteById(Long id) {ventaDetailRepository.deleteById(id);}

    @Override
    public VentaDetail findById(Long id) {return ventaDetailRepository.findById(id).orElse(null);}

    @Override
    public List<VentaDetail> findAll() {
        return (List<VentaDetail>) ventaDetailRepository.findAll();
    }
}
