package com.medicure.patientservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version; // ✅ Add this import
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
    private Long version;  // ✅ For optimistic locking
}
