package com.alexandrova.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void BeforeAll(){
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }
}
