package com.microservice.dispersion.services;

import com.microservice.dispersion.client.PatientClient;
import com.microservice.dispersion.client.ProductClient;
import com.microservice.dispersion.dto.PatientsDTO;
import com.microservice.dispersion.dto.ProductsDTO;
import com.microservice.dispersion.entities.Dispersion;
import com.microservice.dispersion.persistence.DispersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispersionServiceImpl implements IDispersionService{

    @Autowired
    DispersionRepository dispersionRepository;

    @Autowired
    ProductClient productoClient;

    @Autowired
    PatientClient pacienteClient;

    @Override
    public Dispersion save(Dispersion disp) {
        // Obtener datos del producto
        ProductsDTO products = productoClient.getProductById(disp.getProductId());
        // Obtener datos del paciente
        PatientsDTO patients = pacienteClient.getPatientById(disp.getPatientId());

        // Aquí puedes hacer validaciones, logs o procesamiento con los datos externos

        return dispersionRepository.save(disp);
    }

    @Override
    public void deleteById(Long id){dispersionRepository.deleteById(id);}

    @Override
    public Dispersion findById(Long id){return dispersionRepository.findById(id).orElse(null);}

    @Override
    public List<Dispersion> findAll(){return (List<Dispersion>) dispersionRepository.findAll();}

}
