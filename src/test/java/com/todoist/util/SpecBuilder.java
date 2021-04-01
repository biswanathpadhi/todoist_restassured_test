package com.todoist.util;

import io.restassured.RestAssured;
import io.restassured.authentication.OAuth2Scheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class SpecBuilder {

    public  RequestSpecification req;

    public RequestSpecification requestSpecification() {
        if (req == null) {
            PrintStream log = null;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));

                req = new RequestSpecBuilder().setBaseUri(ConfigReader.readValueFromPropertyFile("baseUri"))
                        .addFilter(RequestLoggingFilter.logRequestTo(log))
                        .addFilter(ResponseLoggingFilter.logResponseTo(log))
                        .setContentType(ContentType.JSON).build();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return req;
    }
}
