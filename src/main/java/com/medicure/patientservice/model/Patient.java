package com.medicure.patientservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    private String patientId;

    private String name;
    private int age;
    private String gender;
    private String disease;

    @Version
    private Long version;

    // Custom constructor to fix Jenkins/Maven compilation error
    public Patient(String patientId, String name, int age, String gender, String disease) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
    }
}
