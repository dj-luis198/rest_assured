package com.restassured.day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Authentications {

    @Test
    void basicAuthentications(){

        given()
            .auth().basic("danielfarias1984", "Az25288@")
        .when()
            .get("https://demoqa.com/Account/v1/Authorized")
        .then()
            .statusCode(200)
            .log().body();
    }

    @Test
    void digestAuthentications(){

        given()
            .auth().digest("danielfarias1984", "Az25288@")
        .when()
            .get("https://demoqa.com/Account/v1/Authorized")
        .then()
            .statusCode(200)
            .log().body();
    }

    @Test
    void preemptiveAuthentications(){

        given()
            .auth().preemptive().basic("danielfarias1984", "Az25288@")
        .when()
            .get("https://demoqa.com/Account/v1/Authorized")
        .then()
            .statusCode(200)
            .log().body();
    }

    @Test
    void bearerTokenAuthentications(){
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6ImRhbmllbGZhcmlhczE5ODQiLCJwYXNzd29yZCI6IkF6MjUyODhAIiwiaWF0IjoxNjk0NDc1ODE4fQ.SB4lvaKdSXzJAW_NVE9sj7HuOJ3qgUS8lLOY_bIs4gU";
        given()
            .pathParam("UUID", "a60afb96-4ac1-4164-aa3e-0d1ff76722c6")
            .auth().basic("danielfarias1984", "Az25288@")
            .header("Authorization", "Bearer " +token)
        .when()
            .get("https://demoqa.com/Account/v1/User/{UUID}")
        .then()
            .statusCode(200)
            .log().body();
    }

    //@Test
    void OAuth1Authentications(){
        given()
            .auth().oauth("consumerKey",  "consumerSecret",  "accessToken",  "secretToken")
        .when()
            .get("url")
        .then()
            .statusCode(200)
            .log().body();
    }

    //@Test
    void apiKeyAuthentications(){
        given()
            .queryParam("appid", "valor key")
        .when()
            .get("url")
        .then()
            .statusCode(200)
            .log().body();
    } 
}
