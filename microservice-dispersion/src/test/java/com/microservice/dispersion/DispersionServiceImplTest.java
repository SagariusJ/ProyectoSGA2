package com.microservice.dispersion;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.microservice.dispersion.client.PatientClient;
import com.microservice.dispersion.client.ProductClient;
import com.microservice.dispersion.dto.PatientsDTO;
import com.microservice.dispersion.dto.ProductsDTO;
import com.microservice.dispersion.entities.Dispersion;
import com.microservice.dispersion.persistence.DispersionRepository;
import com.microservice.dispersion.services.DispersionServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DispersionServiceImplTest {

    @Mock
    private DispersionRepository dispersionRepository;

    @Mock
    private ProductClient productClient;

    @Mock
    private PatientClient patientClient;

    @InjectMocks
    private DispersionServiceImpl dispersionService;

    private Dispersion dispersion;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        dispersion = new Dispersion();
        dispersion.setId(1L);
        dispersion.setProductId(100L);
        dispersion.setPatientId(200L);
        dispersion.setCantidad(5);
        dispersion.setFecha(java.time.LocalDate.now());
    }

    @Test
    void save_shouldCallClientsAndRepository() {
        // Arrange: mock responses from clients
        ProductsDTO productDTO = new ProductsDTO();
        productDTO.setId(100L);
        productDTO.setProduct_name("Producto Test");
        productDTO.setDescription("Descripción Test");

        PatientsDTO patientDTO = new PatientsDTO();
        patientDTO.setId(200L);
        patientDTO.setNombre("Paciente Test");
        patientDTO.setRut("12345678-9");

        when(productClient.getProductById(100L)).thenReturn(productDTO);
        when(patientClient.getPatientById(200L)).thenReturn(patientDTO);
        when(dispersionRepository.save(dispersion)).thenReturn(dispersion);

        // Act
        Dispersion result = dispersionService.save(dispersion);

        // Assert
        assertNotNull(result);
        assertEquals(dispersion.getId(), result.getId());
        verify(productClient).getProductById(100L);
        verify(patientClient).getPatientById(200L);
        verify(dispersionRepository).save(dispersion);
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        Long id = 1L;
        doNothing().when(dispersionRepository).deleteById(id);

        dispersionService.deleteById(id);

        verify(dispersionRepository).deleteById(id);
    }

    @Test
    void findById_shouldReturnDispersion() {
        when(dispersionRepository.findById(1L)).thenReturn(Optional.of(dispersion));

        Dispersion result = dispersionService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(dispersionRepository).findById(1L);
    }

    @Test
    void findAll_shouldReturnList() {
        List<Dispersion> list = Arrays.asList(dispersion);
        when(dispersionRepository.findAll()).thenReturn(list);

        List<Dispersion> result = dispersionService.findAll();

        assertEquals(1, result.size());
        verify(dispersionRepository).findAll();
    }

}
