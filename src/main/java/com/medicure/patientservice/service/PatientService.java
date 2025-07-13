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
         System.out.println("👉 Registering patient: " + patient); // Add this log
        try {
            Patient savedPatient = repository.save(patient);
            System.out.println("✅ Saved patient: " + savedPatient); // Log after saving
            return savedPatient;
        } catch (Exception e) {
            System.err.println("❌ Error while saving patient: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw for visibility in controller
        }
    }
    

    public Patient update(String id, Patient patient) {
        Patient existing = repository.findById(id).orElseThrow();
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
        register(new Patient("P001", "Amit", 35, "Male", "Diabetes"));
        register(new Patient("P002", "Sita", 29, "Female", "Fever"));
    }
}
