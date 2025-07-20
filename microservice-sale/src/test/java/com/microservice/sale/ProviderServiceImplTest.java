package com.microservice.sale;

import com.microservice.sale.entities.Provider;
import com.microservice.sale.persistence.ProviderRepository;
import com.microservice.sale.services.ProviderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProviderServiceImplTest {

    @InjectMocks
    private ProviderServiceImpl service;

    @Mock
    private ProviderRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Provider provider = new Provider();
        when(repository.save(provider)).thenReturn(provider);
        assertEquals(provider, service.save(provider));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        Provider provider = new Provider();
        when(repository.findById(1L)).thenReturn(Optional.of(provider));
        assertEquals(provider, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Provider(), new Provider()));
        assertEquals(2, service.findAll().size());
    }
}
