package com.example.api.utils;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class RestConfig {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
    }
}
