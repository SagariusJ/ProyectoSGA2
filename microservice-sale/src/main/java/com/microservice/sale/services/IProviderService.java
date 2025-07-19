package com.microservice.sale.services;

import com.microservice.sale.entities.Provider;

import java.util.List;

public interface IProviderService {

    Provider save(Provider provider);

    void deleteById(Long id);

    Provider findById(Long id);

    List<Provider> findAll();
}
