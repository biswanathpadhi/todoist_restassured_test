package com.todoist.stepdefinitions;

import com.todoist.util.ConfigReader;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class Presteps {
    public String getToken(String code) {

        RestAssured.baseURI = ConfigReader.readValueFromPropertyFile("accessTokenUrl");

        String client_id = ConfigReader.readValueFromPropertyFile("clientId");
        String client_secret = ConfigReader.readValueFromPropertyFile("clientSecret");

        String accessToken = given()
                .queryParam("client_id", client_id)
                .queryParam("client_secret", client_secret)
                .queryParam("code", code)
                .log().all()
                .when()
                .post(ConfigReader.readValueFromPropertyFile("accessTokenUrl"))
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response().jsonPath().getString("access_token");

        return accessToken;

    }

}
