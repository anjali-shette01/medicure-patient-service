package com.medicure.patientservice.service;

import com.medicure.patientservice.model.Patient;
import com.medicure.patientservice.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {

    private PatientRepository patientRepository;
    private PatientService patientService;

    @BeforeEach
    public void setup() {
        patientRepository = mock(PatientRepository.class);
        patientService = new PatientService();

        // Inject mock repository using reflection (or use constructor-based injection in real project)
        try {
            java.lang.reflect.Field field = PatientService.class.getDeclaredField("repository");
            field.setAccessible(true);
            field.set(patientService, patientRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRegisterPatient() {
        Patient patient = new Patient("P100", "Rahul", 40, "Male", "Flu");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient result = patientService.register(patient);

        assertEquals("Rahul", result.getName());
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    public void testUpdatePatient() {
        Patient oldPatient = new Patient("P200", "Neha", 25, "Female", "Cold");
        Patient updated = new Patient("P200", "Neha Singh", 26, "Female", "Cough");

        when(patientRepository.findById("P200")).thenReturn(Optional.of(oldPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(updated);

        Patient result = patientService.update("P200", updated);

        assertEquals("Neha Singh", result.getName());
        assertEquals(26, result.getAge());
        assertEquals("Cough", result.getDisease());
    }

    @Test
    public void testSearchByName() {
        Patient p1 = new Patient("P001", "Amit", 35, "Male", "Diabetes");

        when(patientRepository.findByName("Amit")).thenReturn(Arrays.asList(p1));

        List<Patient> results = patientService.searchByName("Amit");

        assertEquals(1, results.size());
        assertEquals("Amit", results.get(0).getName());
    }

    @Test
    public void testDeletePatient() {
        String id = "P002";

        doNothing().when(patientRepository).deleteById(id);

        patientService.delete(id);

        verify(patientRepository, times(1)).deleteById(id);
    }
}
