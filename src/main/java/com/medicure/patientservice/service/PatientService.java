package com.medicure.patientservice.service;

import com.medicure.patientservice.model.Patient;
import com.medicure.patientservice.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public Patient register(Patient patient) {
        System.out.println("👉 Registering patient: " + patient);
        try {
            if (patient.getPatientId() == null || patient.getPatientId().isBlank()) {
                throw new IllegalArgumentException("Patient ID must not be null or empty.");
            }

            if (repository.existsById(patient.getPatientId())) {
                throw new IllegalArgumentException("Patient with ID " + patient.getPatientId() + " already exists.");
            }

            Patient savedPatient = repository.save(patient);
            System.out.println("✅ Saved patient: " + savedPatient);
            return savedPatient;

        } catch (Exception e) {
            System.err.println("❌ Error while saving patient: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Patient update(String id, Patient patient) {
        Patient existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient with ID " + id + " not found."));
        
        existing.setName(patient.getName());
        existing.setAge(patient.getAge());
        existing.setGender(patient.getGender());
        existing.setDisease(patient.getDisease());

        return repository.save(existing);
    }

    public List<Patient> searchByName(String name) {
        return repository.findByName(name);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    @PostConstruct
    public void preloadData() {
        if (!repository.existsById("P001")) {
            register(new Patient("P001", "Amit", 35, "Male", "Diabetes"));
        } else {
            System.out.println("⚠️ Patient P001 already exists.");
        }

        if (!repository.existsById("P002")) {
            register(new Patient("P002", "Sita", 29, "Female", "Fever"));
        } else {
            System.out.println("⚠️ Patient P002 already exists.");
        }
    }
}
