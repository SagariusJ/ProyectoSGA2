package com.microservice.sale;

import com.microservice.sale.entities.Venta;
import com.microservice.sale.persistence.VentaRepository;
import com.microservice.sale.services.VentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaServiceImplTest {

    @InjectMocks
    private VentaServiceImpl service;

    @Mock
    private VentaRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Venta venta = new Venta();
        when(repository.save(venta)).thenReturn(venta);
        assertEquals(venta, service.save(venta));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        Venta venta = new Venta();
        when(repository.findById(1L)).thenReturn(Optional.of(venta));
        assertEquals(venta, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Venta(), new Venta()));
        assertEquals(2, service.findAll().size());
    }
}
