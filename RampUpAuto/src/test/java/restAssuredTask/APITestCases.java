package com.restAssuredTask;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

public class APITestCases {

    @Test
    public void TC_1_validateUserAPI(){
     Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users")
                .then()
                .assertThat().statusCode(200)
             .extract().response();
     List idsList = response.jsonPath().get("id");
     int idsCount = idsList.size();

     for(int i=0; i<idsCount; i++){
         int getIDs = response.jsonPath().get("[" + i + "].id");
         String getNames = response.jsonPath().get("[" + i + "].name");
         System.out.println("User Data " + getIDs + " : " + getNames);
     }




    }

   @Test
   public void TC_2_validatePostsAPI(){
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("every {it.userid = null }", is(false))
                .body("every {it.id = null }", is(false))
                .body("every {it.title = null }", is(false))
                .body("every {it.body = null }", is(false))
                .body("[16].title", equalTo("fugit voluptas sed molestias voluptatem provident"));
    }




}
