package com.jan1ooo.apiclickbus.place;

import io.restassured.http.ContentType;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlaceTest {

    @Test
    public void testWhenIRegisterPlaceThenIGetStatusCode201(){
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

    @Test
    public void testWhenSearchAllPlaceIGetStatusCode200(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Listar Places
        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testWhenRegisteringPlaceMissingFieldIObtainStatusCode400(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Cadastrar Place
        given().body("{\n" +
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
                .statusCode(400);

    }

    @Test
    public void testWhenIRegisterPlaceWithEmptyInformationIGetStatusCode400(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Cadastrar Place
        given().body("{\n" +
                        "    \"name\": \"\",\n" +
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
                .statusCode(400);
    }

    @Test
    public void testWhenLookingForAPlaceThatContainsTheNameStatusCode200(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Place Especifico
        given().queryParam("name","Place")
                .get("/search")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void testWhenSearchingForASpecificPlaceNotExistingIGetStatusCode404(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Place
        given().queryParam("name","Place Ten")
                .get("/specific/search")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404);
    }

    @Test
    public void testWhenIUpdatPlaceNotExistingIGetStatusCode400(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Update Place
        given().queryParam("name", "Place Ten")
                .and()
                .body("{}")
                .contentType(ContentType.JSON)
                .put("/search")
                .then()
                .log().all()
                .statusCode(404);
    }

    /*@Test
    public void testWhenIUpdatePlaceIGetStatusCode200(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Update Place
        given().queryParam("name", "Place One")
                .and()
                .body("{\n" +
                        "    \"name\": \"Place One\",\n" +
                        "    \"slug\": \"GJH2F2\",\n" +
                        "    \"city\": \"São Paulo\",\n" +
                        "    \"state\": \"SP\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .put("/search")
                .then()
                .log().all()
                .statusCode(200);
    }*/

    /*@Test
    @AfterEach
    public void testWhenSearchingForASpecificExistingPlaceIGetStatusCode200(){
        // Configuraro caminho commum de acesso a minha API rest
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api/place";

        // Place Especifico
        given().queryParam("name","Place One")
                .get("/specific/search")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }*/

}
