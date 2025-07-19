package com.microservice.caja.services;

import com.microservice.caja.entities.Caja;
import com.microservice.caja.persistence.CajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CajaServiceImpl implements ICajaService{

    @Autowired
    CajaRepository cajaRepository;

    @Override
    public Caja save(Caja caja){ return cajaRepository.save(caja);}

    @Override
    public void deleteById(Long id){cajaRepository.deleteById(id);}

    @Override
    public Caja findById(Long id){return cajaRepository.findById(id).orElse(null);}

    @Override
    public List<Caja> findAll(){return (List<Caja>) cajaRepository.findAll();}
}
