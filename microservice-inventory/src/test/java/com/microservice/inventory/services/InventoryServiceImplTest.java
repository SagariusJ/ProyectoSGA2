package com.microservice.inventory.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.microservice.inventory.entities.Inventory;
import com.microservice.inventory.entities.Products;
import com.microservice.inventory.persistence.InventoryRepository;
import com.microservice.inventory.services.InventoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

class InventoryServiceImplTest {

    @Mock
    InventoryRepository inventoryRepository;

    InventoryServiceImpl inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventoryService = new InventoryServiceImpl();
        inventoryService.inventoryRepository = inventoryRepository;
    }

    @Test
    void testSave() {
        Products product = Products.builder()
                .id(1L)
                .product_name("Test Product")
                .description("Test Desc")
                .type("Type A")
                .measure_unit("kg")
                .build();

        Inventory inventory = Inventory.builder()
                .id(1L)
                .product(product)
                .inventory_number("LOT123")
                .exp_date(LocalDate.now().plusDays(10))
                .unit_price(100.0)
                .build();

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        Inventory saved = inventoryService.save(inventory);
        assertNotNull(saved);
        assertEquals("Test Product", saved.getProduct().getProduct_name());
        assertEquals("LOT123", saved.getInventory_number());
    }

    @Test
    void testFindById() {
        Products product = Products.builder()
                .id(1L)
                .product_name("Test Product")
                .build();

        Inventory inventory = Inventory.builder()
                .id(1L)
                .product(product)
                .inventory_number("LOT123")
                .build();

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        Inventory found = inventoryService.findById(1L);
        assertNotNull(found);
        assertEquals("Test Product", found.getProduct().getProduct_name());
    }

    @Test
    void testFindAll() {
        Products product1 = Products.builder()
                .id(1L)
                .product_name("Product 1")
                .build();

        Products product2 = Products.builder()
                .id(2L)
                .product_name("Product 2")
                .build();

        Inventory inv1 = Inventory.builder().id(1L).product(product1).build();
        Inventory inv2 = Inventory.builder().id(2L).product(product2).build();

        List<Inventory> inventoryList = Arrays.asList(inv1, inv2);

        when(inventoryRepository.findAll()).thenReturn(inventoryList);

        List<Inventory> foundList = inventoryService.findAll();
        assertEquals(2, foundList.size());
        assertEquals("Product 1", foundList.get(0).getProduct().getProduct_name());
        assertEquals("Product 2", foundList.get(1).getProduct().getProduct_name());
    }

    @Test
    void testDeleteById() {
        doNothing().when(inventoryRepository).deleteById(1L);
        inventoryService.deleteById(1L);
        verify(inventoryRepository, times(1)).deleteById(1L);
    }
}
