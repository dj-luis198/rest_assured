package com.restassured.day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {

    //primera forma para JSON simples
    @Test
    void testJSONResponse1(){

        given()
            .contentType("ContentType.JSON")
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .statusCode(200)
            .header("Content-Type", "application/json; charset=utf-8")
            .body("data[4].email",equalTo("george.edwards@reqres.in"));
    }

    //Segunda forma para JSON mas complejos
    @Test
    void testJSONResponse2(){

        Response res= given()
                        .contentType(ContentType.JSON)
                      .when()
                        .get("https://reqres.in/api/users?page=2");
        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
        String email= res.jsonPath().get("data[4].email").toString();
        Assert.assertEquals(email,"george.edwards@reqres.in");        
    }

    //aplicamos la segunda forma y ademas realizamos busquedadas mas camplejas con JSONObject class
    @Test
    void testJSONResponse3(){
        Response res= given()
                        .contentType("Content-Type.JSON")
                      .when()
                        .get("https://reqres.in/api/users?page=2");
        Boolean status= false;
        JSONObject jo = new JSONObject(res.asString());
        for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
            String data= jo.getJSONArray("data").getJSONObject(i).get("email").toString();
            if(data.equals("george.edwards@reqres.in")){
                status = true;
                break;
            }
        } 
        Assert.assertTrue(status);
    }
    
}
