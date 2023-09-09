package com.restassured.day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LoggingDemo {
    
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
            //.log().body();
            //.log().cookies();
            //.log().all();
            //.log().headers();
            .log().status();
    }
}
