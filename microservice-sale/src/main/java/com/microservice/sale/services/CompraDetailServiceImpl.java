package com.microservice.sale.services;

import com.microservice.sale.entities.CompraDetail;
import com.microservice.sale.persistence.CompraDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraDetailServiceImpl implements ICompraDetailService{

    @Autowired
    CompraDetailRepository compraDetailRepository;

    @Override
    public CompraDetail save(CompraDetail compraDetail) {
        return compraDetailRepository.save(compraDetail);
    }

    @Override
    public void deleteById(Long id) {
        compraDetailRepository.deleteById(id);
    }

    @Override
    public CompraDetail findById(Long id) {
        return compraDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<CompraDetail> findAll() {
        return (List<CompraDetail>) compraDetailRepository.findAll();
    }
}
