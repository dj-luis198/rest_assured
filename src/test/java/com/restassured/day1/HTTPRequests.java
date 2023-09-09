package com.restassured.day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {
    int id;

    @Test(priority = 1)
    void getUsers() {
        given()
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .and()
            .body("total",equalTo(12))
            .log().body();
    }

    @Test(priority = 2)
    void createUser() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Daniel");
        data.put("job", "trainer");

        id = given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");
    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Luis");
        data.put("job", "teacher");
        given()
            .contentType("application/json")
            .body(data)
        .when()
            .put("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(200)
            .body("name", equalTo("Luis"))
            .and()
            .body("job",equalTo("teacher"))
            .log().body();
    }

    @Test(priority = 4, dependsOnMethods = {"updateUser"})
    void deleteUser() {
        given()
        .when()
            .delete("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(204)
            .log().all();
    }
}
