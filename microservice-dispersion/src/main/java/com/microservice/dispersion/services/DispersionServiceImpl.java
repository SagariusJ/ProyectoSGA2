package com.microservice.dispersion.services;

import com.microservice.dispersion.entities.Dispersion;
import com.microservice.dispersion.persistence.DispersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispersionServiceImpl implements IDispersionService{

    @Autowired
    DispersionRepository dispersionRepository;

    @Override
    public Dispersion save(Dispersion disp){return dispersionRepository.save(disp);}

    @Override
    public void deleteById(Long id){dispersionRepository.deleteById(id);}

    @Override
    public Dispersion findById(Long id){return dispersionRepository.findById(id).orElse(null);}

    @Override
    public List<Dispersion> findAll(){return (List<Dispersion>) dispersionRepository.findAll();}

}
