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
        return repository.save(patient);
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
