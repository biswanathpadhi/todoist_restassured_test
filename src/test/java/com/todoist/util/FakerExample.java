package com.todoist.util;

import com.github.javafaker.Faker;

public class FakerExample {

    /**
     * Use of Faker to generate random objects for api testing
     * @param args
     */
    public static void main(String[] args) {
        String name = String.valueOf(new Faker().company().name());
        System.out.println(name);
    }
}
