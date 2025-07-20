package com.microservice.sale;

import com.microservice.sale.entities.CompraDetail;
import com.microservice.sale.persistence.CompraDetailRepository;
import com.microservice.sale.services.CompraDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompraDetailServiceImplTest {

    @InjectMocks
    private CompraDetailServiceImpl service;

    @Mock
    private CompraDetailRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        CompraDetail detail = new CompraDetail();
        when(repository.save(detail)).thenReturn(detail);
        assertEquals(detail, service.save(detail));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        CompraDetail detail = new CompraDetail();
        when(repository.findById(1L)).thenReturn(Optional.of(detail));
        assertEquals(detail, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new CompraDetail(), new CompraDetail()));
        assertEquals(2, service.findAll().size());
    }
}
