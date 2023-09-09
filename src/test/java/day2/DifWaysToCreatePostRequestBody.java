package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*
 * Different ways to create request body
 * HashMap
 * using Org.JSON librery
 * using POJO class
 * Using external json file data
 */
public class DifWaysToCreatePostRequestBody {
    String id;

    //------------------------------HashMap---------------------------

    //@Test(priority = 1)
    void testPostUsingHashMap(){
       
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Daniel");
        data.put("job", "trainer");
        //si necesito pasar una cadena de valores 
        /*
        String list[]={"dato1","dato2"};
        data.put("lista",list);
        */
        id=given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .body("name",equalTo("Daniel"))
            .body("job",equalTo("trainer"))
            .header("Content-Type","application/json; charset=utf-8")
            .log().all()
            .extract().path("id");
            /* 
            .body("lista[0]",equalTo("dato1"))
            .body("lista[1]",equalTo("dato2"))
            */
    }

    //-----------------------using Org.JSON library------------------------

    //@Test(priority = 1)
    void testPostUsingJsonLibrery(){
        JSONObject data = new JSONObject(); 
        data.put("name", "Daniel");
        data.put("job", "trainer");
        //si necesito pasar una cadena de valores 
        /*
        String list[]={"dato1","dato2"};
        data.put("lista",list);
        */
        id=given()
            .contentType("application/json")
            .body(data.toString())
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .body("name",equalTo("Daniel"))
            .body("job",equalTo("trainer"))
            .header("Content-Type","application/json; charset=utf-8")
            .log().all()
            .extract().path("id");
            /* 
            .body("lista[0]",equalTo("dato1"))
            .body("lista[1]",equalTo("dato2"))
            */
    }

    //-----------------------using POJO class--------------------------------

    //@Test(priority = 1)
    void testPostUsingPOJOClass(){
        Pojo_PostRequest data = new Pojo_PostRequest(); 
        data.setName("Daniel");
        data.setJob("trainer");
        //si necesito pasar una cadena de valores 
        /*
        String list[]={"dato1","dato2"};
        data.setlista(list);
        */
        id=given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .body("name",equalTo("Daniel"))
            .body("job",equalTo("trainer"))
            .header("Content-Type","application/json; charset=utf-8")
            .log().all()
            .extract().path("id");
            /* 
            .body("lista[0]",equalTo("dato1"))
            .body("lista[1]",equalTo("dato2"))
            */
    }

    //-----------------------using JSON File--------------------------------

    @Test(priority = 1)
    void testPostUsingJsonFile() throws FileNotFoundException{
        File f= new File("./jsonFiles/body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);
        id=given()
            .contentType("application/json")
            .body(data.toString())
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .body("name",equalTo("Daniel"))
            .body("job",equalTo("trainer"))
            .header("Content-Type","application/json; charset=utf-8")
            .log().all()
            .extract().path("id");
    }

    @Test(priority = 2)
    void deleteUser() {
        given()
        .when()
            .delete("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(204)
            .log().all();
    }

    
}
