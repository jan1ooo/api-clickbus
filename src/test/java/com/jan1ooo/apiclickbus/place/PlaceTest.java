package com.jan1ooo.apiclickbus.place;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PlaceTest {

    @Test
    public void testQuandoCadastroPlaceEntaoObtenhoStatusCode201(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";
        // Cadastrar Place
        given().body("{\n" +
                        "    \"name\": \"Place One\",\n" +
                        "    \"slug\": \"GJH2F2C\",\n" +
                        "    \"city\": \"São Paulo\",\n" +
                        "    \"state\": \"SP\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);

    }
}
