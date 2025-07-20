package com.microservice.sale;

import com.microservice.sale.entities.Compra;
import com.microservice.sale.entities.CompraDetail;
import com.microservice.sale.persistence.CompraRepository;
import com.microservice.sale.services.CompraServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompraServiceImplTest {

    @InjectMocks
    private CompraServiceImpl compraService;

    @Mock
    private CompraRepository compraRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSetDetailsCompra() {
        Compra compra = new Compra();
        CompraDetail detail = new CompraDetail();
        compra.setDetails(Arrays.asList(detail));

        when(compraRepository.save(compra)).thenReturn(compra);

        Compra saved = compraService.save(compra);

        assertEquals(compra, saved);
        assertEquals(compra, detail.getCompra());
    }

    @Test
    void deleteById_ShouldCallRepository() {
        Long id = 1L;
        compraService.deleteById(id);
        verify(compraRepository).deleteById(id);
    }

    @Test
    void findById_ShouldReturnCompra() {
        Long id = 1L;
        Compra compra = new Compra();
        when(compraRepository.findById(id)).thenReturn(Optional.of(compra));

        Compra result = compraService.findById(id);
        assertEquals(compra, result);
    }

    @Test
    void findAll_ShouldReturnList() {
        when(compraRepository.findAll()).thenReturn(Arrays.asList(new Compra(), new Compra()));
        assertEquals(2, compraService.findAll().size());
    }
}
