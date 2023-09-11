package com.restassured.day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Pojo-------> JSON object ------>Pojo

public class SerilizationDeserilization {

    @Test
    void convertPojoJson() throws JsonProcessingException{

        //created java object using pojo

        DataPerson dataPojo = new DataPerson(); 
        dataPojo.setName("Daniel");
        dataPojo.setJob("trainer");

        //convert java object ---->json object (serilization) (jackson)

        ObjectMapper objMapper = new ObjectMapper();
        String jsonData=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataPojo);
        System.out.println(jsonData);       
    }

    @Test
    void convertJsonPojo() throws JsonProcessingException{

        String jsondata= "{\r\n" + //
                "  \"name\" : \"Daniel\",\r\n" + //
                "  \"job\" : \"trainer\"\r\n" + //
                "}";
            
        //convert json data ----->pojo object

        ObjectMapper objMapper= new ObjectMapper();
        DataPerson dataPerson= objMapper.readValue(jsondata,DataPerson.class);
        System.out.println(dataPerson.getJob());
        System.out.println(dataPerson.getName());

    }
    
}
