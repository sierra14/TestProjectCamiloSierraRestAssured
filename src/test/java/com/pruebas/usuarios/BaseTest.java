package com.pruebas.usuarios;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected RequestSpecification request;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
        request = RestAssured.given().header("Content-Type", "application/json");
    }
}
