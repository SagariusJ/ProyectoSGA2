package com.microservice.fraccionamiento.services;

import com.microservice.fraccionamiento.entities.Fraccionamiento;
import com.microservice.fraccionamiento.persistence.FraccionamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraccionamientoServiceImpl implements IFraccionamientoService{

    @Autowired
    FraccionamientoRepository fraccionamientoRepository;

    @Override
    public Fraccionamiento save(Fraccionamiento frac) {return fraccionamientoRepository.save(frac);}

    @Override
    public void deleteById(Long id) {fraccionamientoRepository.deleteById(id);}

    @Override
    public Fraccionamiento findById(Long id) {return fraccionamientoRepository.findById(id).orElse(null);}

    @Override
    public List<Fraccionamiento> findAll() {return (List<Fraccionamiento>) fraccionamientoRepository.findAll();}
}
