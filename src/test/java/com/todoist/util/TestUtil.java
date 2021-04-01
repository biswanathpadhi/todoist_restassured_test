package com.todoist.util;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class TestUtil {

    public static String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }

    /**
     * Returns the key from the response
     * @param response
     * @param key
     * @return
     */
    public String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    /**
     * Generates a random string of length provided
     * @param length
     * @return random string
     */
    public String getRandomString(int length) {
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    /**
     * Generates a random string of length provided
     * @return random string
     */
    public String getRandomString() {
       return getRandomString(10);
    }

    /**
     * Generates a random number of length value
     * @param length
     * @return the random number
     */
    public long getRandomNumber(int length){
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return Long.parseLong(generatedString);

    }

    /**
     * Generates a 10 digit random number
     * @return  the random number
     */
    public long getRandomNumber(){
        return getRandomNumber(10);
    }

    /**
     * returns a random boolean value
     * @return
     */
    public boolean getRandomBoolean(){ return new Random().nextBoolean();}

}
