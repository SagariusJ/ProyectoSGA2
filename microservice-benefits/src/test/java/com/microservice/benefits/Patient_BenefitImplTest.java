package com.microservice.benefits;

import com.microservice.benefits.entities.Patient_Benefit;
import com.microservice.benefits.persistence.Patient_BenefitRepository;
import com.microservice.benefits.services.Patient_BenefitImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Patient_BenefitImplTest {

    @InjectMocks
    private Patient_BenefitImpl service;

    @Mock
    private Patient_BenefitRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Patient_Benefit entity = new Patient_Benefit();
        when(repository.save(entity)).thenReturn(entity);
        assertEquals(entity, service.save(entity));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        Patient_Benefit entity = new Patient_Benefit();
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        assertEquals(entity, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Patient_Benefit(), new Patient_Benefit()));
        assertEquals(2, service.findAll().size());
    }
}
