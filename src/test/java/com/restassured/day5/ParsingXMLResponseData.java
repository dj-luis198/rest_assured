package com.restassured.day5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ParsingXMLResponseData {


    //Forma1 para xml simples
    @Test
    void testXMLResponse(){
        given()
        .when()
            .get("http://restapi.adequateshop.com/api/Traveler?page=1")
        .then()
            .statusCode(200)
            .header("Content-Type", "application/xml; charset=utf-8")
            .body("TravelerinformationResponse.page",equalTo("1"))
            .body("TravelerinformationResponse.travelers.Travelerinformation[2].name",equalTo("vano"));
    }

    //Forma1 para xml complejos usando variable
    @Test
    void testXMLResponse2(){
        Response res=
        given()
        .when()
            .get("http://restapi.adequateshop.com/api/Traveler?page=1");
        
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.getHeader("Content-Type"), "application/xml; charset=utf-8");
        String page=res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(page, "1");
        String name=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[2].name").toString();
        Assert.assertEquals(name, "vano");
    }

    //Busqueda y validacion xml XmlPath class
    @Test
    void testXMLResponse3(){
        Boolean status = false;
        Response res=
        given()
        .when()
            .get("http://restapi.adequateshop.com/api/Traveler?page=1");
        XmlPath xmlobj= new XmlPath(res.asString());
        List <String> travelers =xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travelers.size(), 10);
        List <String> names =xmlobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        for (String name : names) {
            if (name.equals("vano")) {
                status=true;
                break;
            }
        }
        Assert.assertTrue(status);
    }
 
}
