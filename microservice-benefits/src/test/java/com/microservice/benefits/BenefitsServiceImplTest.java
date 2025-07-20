package com.microservice.benefits;

import com.microservice.benefits.entities.Benefits;
import com.microservice.benefits.persistence.BenefitsRepository;
import com.microservice.benefits.services.BenefitsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BenefitsServiceImplTest {

    @InjectMocks
    private BenefitsServiceImpl service;

    @Mock
    private BenefitsRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Benefits benefit = new Benefits();
        when(repository.save(benefit)).thenReturn(benefit);
        assertEquals(benefit, service.save(benefit));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        Benefits benefit = new Benefits();
        when(repository.findById(1L)).thenReturn(Optional.of(benefit));
        assertEquals(benefit, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Benefits(), new Benefits()));
        assertEquals(2, service.findAll().size());
    }
}
