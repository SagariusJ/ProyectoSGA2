package com.microservice.inventory.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.microservice.inventory.entities.Products;
import com.microservice.inventory.entities.StockWare;
import com.microservice.inventory.entities.Inventory;
import com.microservice.inventory.entities.Warehouse;
import com.microservice.inventory.persistence.StockWareRepository;
import com.microservice.inventory.services.StockWareServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

class StockWareServiceImplTest {

    @Mock
    StockWareRepository stockWareRepository;

    StockWareServiceImpl stockWareService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stockWareService = new StockWareServiceImpl();
        stockWareService.stockWareRepository = stockWareRepository;
    }

    @Test
    void testSave() {
        Warehouse warehouse = Warehouse.builder().id(1L).warehouse_Name("W1").build();
        Products product = Products.builder().id(1L).product_name("P1").build();
        Inventory inventory = Inventory.builder().id(1L).product(product).build();

        StockWare stockWare = StockWare.builder()
                .id(1L)
                .amount(100)
                .warehouse(warehouse)
                .lot(inventory)
                .build();

        when(stockWareRepository.save(stockWare)).thenReturn(stockWare);

        StockWare saved = stockWareService.save(stockWare);

        assertNotNull(saved);
        assertEquals(100, saved.getAmount());
        assertEquals("W1", saved.getWarehouse().getWarehouse_Name());
        assertEquals("P1", saved.getLot().getProduct().getProduct_name());
    }

    @Test
    void testFindById() {
        Warehouse warehouse = Warehouse.builder().id(1L).warehouse_Name("W1").build();
        Products product = Products.builder().id(1L).product_name("P1").build();
        Inventory inventory = Inventory.builder().id(1L).product(product).build();

        StockWare stockWare = StockWare.builder()
                .id(1L)
                .amount(100)
                .warehouse(warehouse)
                .lot(inventory)
                .build();

        when(stockWareRepository.findById(1L)).thenReturn(Optional.of(stockWare));

        StockWare found = stockWareService.findById(1L);

        assertNotNull(found);
        assertEquals(100, found.getAmount());
    }

    @Test
    void testFindAll() {
        Warehouse warehouse = Warehouse.builder().id(1L).warehouse_Name("W1").build();
        Products product = Products.builder().id(1L).product_name("P1").build();

        StockWare s1 = StockWare.builder().id(1L).amount(10).warehouse(warehouse).lot(
                Inventory.builder().id(1L).product(product).build()
        ).build();

        StockWare s2 = StockWare.builder().id(2L).amount(20).warehouse(warehouse).lot(
                Inventory.builder().id(2L).product(product).build()
        ).build();

        List<StockWare> stockList = Arrays.asList(s1, s2);

        when(stockWareRepository.findAll()).thenReturn(stockList);

        List<StockWare> all = stockWareService.findAll();

        assertEquals(2, all.size());
        assertEquals(10, all.get(0).getAmount());
        assertEquals(20, all.get(1).getAmount());
    }

    @Test
    void testDeleteById() {
        doNothing().when(stockWareRepository).deleteById(1L);

        stockWareService.deleteById(1L);

        verify(stockWareRepository, times(1)).deleteById(1L);
    }
}
