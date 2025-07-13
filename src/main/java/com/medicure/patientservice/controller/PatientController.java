package com.medicure.patientservice.controller;

import com.medicure.patientservice.model.Patient;
import com.medicure.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping("/registerPatient")
    public Patient register(@RequestBody Patient patient) {
    System.out.println("Received Patient: " + patient);
        try {
            return service.register(patient);
    }   catch (Exception e) {
         e.printStackTrace();
        throw e;
    }
}


    @PutMapping("/updatePatient/{id}")
    public Patient update(@PathVariable String id, @RequestBody Patient patient) {
        return service.update(id, patient);
    }

    @GetMapping("/searchPatient/{name}")
    public List<Patient> search(@PathVariable String name) {
        return service.searchByName(name);
    }

    @DeleteMapping("/deletePatient/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}

