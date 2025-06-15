package com.microservice.inventory.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.microservice.inventory.entities.Warehouse;
import com.microservice.inventory.persistence.WarehouseRepository;
import com.microservice.inventory.services.WarehouseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

class WarehouseServiceImplTest {

    @Mock
    WarehouseRepository warehouseRepository;

    WarehouseServiceImpl warehouseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        warehouseService = new WarehouseServiceImpl();
        warehouseService.warehouseRepository = warehouseRepository;
    }

    @Test
    void testSave() {
        Warehouse warehouse = Warehouse.builder()
                .id(1L)
                .warehouse_Name("Main Warehouse")
                .direction("123 Street")
                .build();

        when(warehouseRepository.save(warehouse)).thenReturn(warehouse);

        Warehouse saved = warehouseService.save(warehouse);

        assertNotNull(saved);
        assertEquals("Main Warehouse", saved.getWarehouse_Name());
    }

    @Test
    void testFindById() {
        Warehouse warehouse = Warehouse.builder()
                .id(1L)
                .warehouse_Name("Main Warehouse")
                .build();

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Warehouse found = warehouseService.findById(1L);

        assertNotNull(found);
        assertEquals("Main Warehouse", found.getWarehouse_Name());
    }

    @Test
    void testFindAll() {
        Warehouse w1 = Warehouse.builder().id(1L).warehouse_Name("W1").build();
        Warehouse w2 = Warehouse.builder().id(2L).warehouse_Name("W2").build();

        List<Warehouse> warehouses = Arrays.asList(w1, w2);

        when(warehouseRepository.findAll()).thenReturn(warehouses);

        List<Warehouse> all = warehouseService.findAll();

        assertEquals(2, all.size());
        assertEquals("W1", all.get(0).getWarehouse_Name());
        assertEquals("W2", all.get(1).getWarehouse_Name());
    }

    @Test
    void testDeleteById() {
        doNothing().when(warehouseRepository).deleteById(1L);

        warehouseService.deleteById(1L);

        verify(warehouseRepository, times(1)).deleteById(1L);
    }
}

