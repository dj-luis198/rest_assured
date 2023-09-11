package com.restassured.day3;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
    
       // @Test
        void testHeaders(){
    
            given()
            .when()
                .get("https://www.google.com/")
            .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");     
        }

         @Test
        void getHeaders(){
    
            Response res=given()
            .when()
                .get("https://www.google.com/");

            // get single header info
            String value_headers= res.getHeader("Content-Type");
            System.out.println("the value of Content-Type is: "+ value_headers);

            // get all headers info
            Headers values_headers= res.getHeaders();
            for(Header hd:values_headers){
                System.out.println(hd.getName()+" "+ hd.getValue());
            }    
        }
}
