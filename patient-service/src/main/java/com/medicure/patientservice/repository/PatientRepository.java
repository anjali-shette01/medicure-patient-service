package com.medicure.patientservice.repository;

import com.medicure.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, String> {
    List<Patient> findByName(String name);
}
