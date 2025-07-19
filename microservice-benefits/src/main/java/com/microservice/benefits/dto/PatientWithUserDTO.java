package com.microservice.benefits.dto;

import com.microservice.benefits.entities.Patients;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientWithUserDTO {
    private Patients patient;
    private UserDTO user;
}
