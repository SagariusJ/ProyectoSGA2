package com.microservice.benefits;

import com.microservice.benefits.client.UserClient;
import com.microservice.benefits.dto.PatientWithUserDTO;
import com.microservice.benefits.dto.UserDTO;
import com.microservice.benefits.entities.Patients;
import com.microservice.benefits.persistence.PatientsRepository;
import com.microservice.benefits.services.PatientsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientsServiceImplTest {

    @InjectMocks
    private PatientsServiceImpl service;

    @Mock
    private PatientsRepository repository;

    @Mock
    private UserClient userClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Patients patient = new Patients();
        when(repository.save(patient)).thenReturn(patient);
        assertEquals(patient, service.save(patient));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        service.deleteById(1L);
        verify(repository).deleteById(1L);
    }

    @Test
    void findById_ShouldReturnEntity() {
        Patients patient = new Patients();
        when(repository.findById(1L)).thenReturn(Optional.of(patient));
        assertEquals(patient, service.findById(1L));
    }

    @Test
    void findAll_ShouldReturnList() {
        when(repository.findAll()).thenReturn(Arrays.asList(new Patients(), new Patients()));
        assertEquals(2, service.findAll().size());
    }

    @Test
    void getPatientWithUser_ShouldReturnDTO() {
        Long patientId = 1L;
        Patients patient = new Patients();
        patient.setUserId(123L);

        UserDTO user = new UserDTO();
        when(repository.findById(patientId)).thenReturn(Optional.of(patient));
        when(userClient.getUserById(123L)).thenReturn(user);

        PatientWithUserDTO result = service.getPatientWithUser(patientId);

        assertEquals(patient, result.getPatient());
        assertEquals(user, result.getUser());
    }

    @Test
    void getPatientWithUser_ShouldThrowIfPatientNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            service.getPatientWithUser(1L);
        });

        assertEquals("Patient not found", ex.getMessage());
    }
}
