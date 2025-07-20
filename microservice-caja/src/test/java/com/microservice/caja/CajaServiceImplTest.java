package com.microservice.caja;

import com.microservice.caja.entities.Caja;
import com.microservice.caja.persistence.CajaRepository;
import com.microservice.caja.services.CajaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//tests
class CajaServiceImplTest {

    @InjectMocks
    private CajaServiceImpl service;

    @Mock
    private CajaRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Caja caja = new Caja();
        when(repository.save(caja)).thenReturn(caja);
        assertEquals(caja, service.save(caja));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        Caja caja = new Caja();
        when(repository.findById(1L)).thenReturn(Optional.of(caja));
        assertEquals(caja, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Caja(), new Caja()));
        assertEquals(2, service.findAll().size());
    }
}
