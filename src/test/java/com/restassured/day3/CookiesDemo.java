package com.restassured.day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class CookiesDemo {

    //@Test
    void testCookies(){

        given()
        .when()
            .get("https://www.google.com/")
        .then()
        .and()
            .cookie("AEC",not("Ad49MVGpmin6SAn-9jOZ2bUzNT-d8OB7x6jLVixvmOPq9cw9YykUqs_MdA"))
            .log().all();
    }

    @Test
    void getCookiesInfo(){
        Response res= given()
        .when()
            .get("https://www.google.com/");

        //get single cookie info
        String cookie_value= res.getCookie("AEC");
        System.out.println("Value cookie AEC is: "+cookie_value);
        //get all cookies
        Map<String, String> cookie_values= res.getCookies();
        System.out.println(cookie_values.keySet());
        for(String k:cookie_values.keySet()){
            String value= res.getCookie(k);
            System.out.println(k+ " "+value);
        }
    }
    
}
