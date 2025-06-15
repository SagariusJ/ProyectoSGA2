package com.microservice.inventory.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.microservice.inventory.entities.Products;
import com.microservice.inventory.persistence.ProductsRepository;
import com.microservice.inventory.services.ProductsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

class ProductsServiceImplTest {

    @Mock
    ProductsRepository productsRepository;

    ProductsServiceImpl productsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productsService = new ProductsServiceImpl();
        productsService.productsRepository = productsRepository;
    }

    @Test
    void testSave() {
        Products product = Products.builder()
                .id(1L)
                .product_name("Product Test")
                .description("Description Test")
                .type("Type1")
                .measure_unit("unit")
                .build();

        when(productsRepository.save(product)).thenReturn(product);

        Products saved = productsService.save(product);

        assertNotNull(saved);
        assertEquals("Product Test", saved.getProduct_name());
    }

    @Test
    void testFindById() {
        Products product = Products.builder()
                .id(1L)
                .product_name("Product Test")
                .build();

        when(productsRepository.findById(1L)).thenReturn(Optional.of(product));

        Products found = productsService.findById(1L);

        assertNotNull(found);
        assertEquals("Product Test", found.getProduct_name());
    }

    @Test
    void testFindAll() {
        Products p1 = Products.builder().id(1L).product_name("P1").build();
        Products p2 = Products.builder().id(2L).product_name("P2").build();

        List<Products> productList = Arrays.asList(p1, p2);

        when(productsRepository.findAll()).thenReturn(productList);

        List<Products> all = productsService.findAll();

        assertEquals(2, all.size());
        assertEquals("P1", all.get(0).getProduct_name());
        assertEquals("P2", all.get(1).getProduct_name());
    }

    @Test
    void testDeleteById() {
        doNothing().when(productsRepository).deleteById(1L);

        productsService.deleteById(1L);

        verify(productsRepository, times(1)).deleteById(1L);
    }
}