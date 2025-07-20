package com.microservice.sale;

import com.microservice.sale.entities.VentaDetail;
import com.microservice.sale.persistence.VentaDetailRepository;
import com.microservice.sale.services.VentaDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaDetailServiceImplTest {

    @InjectMocks
    private VentaDetailServiceImpl service;

    @Mock
    private VentaDetailRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        VentaDetail detail = new VentaDetail();
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
        VentaDetail detail = new VentaDetail();
        when(repository.findById(1L)).thenReturn(Optional.of(detail));
        assertEquals(detail, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new VentaDetail(), new VentaDetail()));
        assertEquals(2, service.findAll().size());
    }
}
