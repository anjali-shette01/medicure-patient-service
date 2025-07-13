package com.medicure.patientservice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class PatientApiTest {

    @Test
    public void testGetAllPatients() {
        Response response = RestAssured
                .given()
                .baseUri("http://localhost:8081")  
                .when()
                .get("/patient/searchPatient/Anjali");


        response.then().statusCode(200);
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void testCreatePatient() {
        String newPatientJson = """
        {
            "name": "Anjali Shette",
            "age": 25,
            "gender": "Female",
            "disease": "Flu"
        }
        """;

        Response response = RestAssured
                .given()
                .baseUri("http://localhost:8081")
                .contentType("application/json")
                .body(newPatientJson)
                .when()
                .post("/patient/registerPatient");


        response.then().statusCode(200); // or 200 if your controller returns OK
    }
}

