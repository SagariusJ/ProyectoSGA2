package com.microservice.fraccionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.microservice.fraccionamiento.client.InventoryClient;
import com.microservice.fraccionamiento.dto.ProductDTO;
import com.microservice.fraccionamiento.dto.WarehouseDTO;
import com.microservice.fraccionamiento.entities.Fraccionamiento;
import com.microservice.fraccionamiento.persistence.FraccionamientoRepository;
import com.microservice.fraccionamiento.services.FraccionamientoServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FraccionamientoServiceImplTest {

    @InjectMocks
    private FraccionamientoServiceImpl service;

    @Mock
    private FraccionamientoRepository repository;

    @Mock
    private InventoryClient inventoryClient;

    private Fraccionamiento fraccionamiento;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        fraccionamiento = new Fraccionamiento();
        fraccionamiento.setId(1L);
        fraccionamiento.setProductoId(1L);
        fraccionamiento.setSucursalOrigenId(10L);
        fraccionamiento.setSucursalDestinoId(20L);
        fraccionamiento.setCantidad(5);
    }

    @Test
    void save_validFraccionamiento_returnsSaved() {
        ProductDTO product = new ProductDTO();
        product.setId(1L);

        WarehouseDTO origen = new WarehouseDTO();
        origen.setId(10L);

        WarehouseDTO destino = new WarehouseDTO();
        destino.setId(20L);

        when(inventoryClient.getProductById(1L)).thenReturn(product);
        when(inventoryClient.getWarehouseById(10L)).thenReturn(origen);
        when(inventoryClient.getWarehouseById(20L)).thenReturn(destino);
        when(repository.save(fraccionamiento)).thenReturn(fraccionamiento);

        Fraccionamiento saved = service.save(fraccionamiento);

        assertNotNull(saved);
        assertEquals(1L, saved.getProductoId());
        verify(inventoryClient).getProductById(1L);
        verify(inventoryClient, times(2)).getWarehouseById(anyLong());
        verify(repository).save(fraccionamiento);
    }

    @Test
    void save_invalidProduct_throwsException() {
        when(inventoryClient.getProductById(1L)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.save(fraccionamiento));
        assertTrue(ex.getMessage().contains("Producto con ID 1 no encontrado"));

        verify(inventoryClient).getProductById(1L);
        verify(inventoryClient, never()).getWarehouseById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void save_invalidSucursalOrigen_throwsException() {
        ProductDTO product = new ProductDTO();
        product.setId(1L);

        when(inventoryClient.getProductById(1L)).thenReturn(product);
        when(inventoryClient.getWarehouseById(10L)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.save(fraccionamiento));
        assertTrue(ex.getMessage().contains("Sucursal origen con ID 10 no encontrada"));

        verify(inventoryClient).getProductById(1L);
        verify(inventoryClient).getWarehouseById(10L);
        verify(inventoryClient, never()).getWarehouseById(20L);
        verify(repository, never()).save(any());
    }

    @Test
    void save_invalidSucursalDestino_throwsException() {
        ProductDTO product = new ProductDTO();
        product.setId(1L);

        WarehouseDTO origen = new WarehouseDTO();
        origen.setId(10L);

        when(inventoryClient.getProductById(1L)).thenReturn(product);
        when(inventoryClient.getWarehouseById(10L)).thenReturn(origen);
        when(inventoryClient.getWarehouseById(20L)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.save(fraccionamiento));
        assertTrue(ex.getMessage().contains("Sucursal destino con ID 20 no encontrada"));

        verify(inventoryClient).getProductById(1L);
        verify(inventoryClient, times(2)).getWarehouseById(anyLong());
        verify(repository, never()).save(any());
    }

    @Test
    void findById_found_returnsFraccionamiento() {
        when(repository.findById(1L)).thenReturn(Optional.of(fraccionamiento));

        Fraccionamiento found = service.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getProductoId());
        verify(repository).findById(1L);
    }

    @Test
    void findById_notFound_returnsNull() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Fraccionamiento found = service.findById(1L);

        assertNull(found);
        verify(repository).findById(1L);
    }

    @Test
    void deleteById_callsRepository() {
        doNothing().when(repository).deleteById(1L);

        service.deleteById(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void findAll_returnsList() {
        List<Fraccionamiento> lista = Collections.singletonList(fraccionamiento);
        when(repository.findAll()).thenReturn(lista);

        List<Fraccionamiento> result = service.findAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
    }
}
