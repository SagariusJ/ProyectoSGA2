package com.microservice.sale.services;

import com.microservice.sale.entities.Provider;
import com.microservice.sale.persistence.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements IProviderService{

    ProviderRepository providerRepository;

    @Override
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void deleteById(Long id) {
        providerRepository.deleteById(id);
    }

    @Override
    public Provider findById(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Provider> findAll() {
        return (List<Provider>) providerRepository.findAll();
    }
}
