package com.restassured.day6;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;


public class JSONSchemaValidation {
    @Test
    void jsonSchemaValidation() {

        given()
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .assertThat().body(matchesJsonSchemaInClasspath("dataJSONSchema.json"));
    }

    
    @Test
    void XMLSchemaValidation() {

        given()
        .when()
            .get("http://restapi.adequateshop.com/api/Traveler?page=1")
        .then()
            .assertThat().body(matchesXsdInClasspath("travels.xsd"));
    }
}
