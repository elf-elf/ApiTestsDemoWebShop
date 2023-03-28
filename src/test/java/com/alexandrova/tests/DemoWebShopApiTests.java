package com.alexandrova.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.alexandrova.tests.TestData.*;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DemoWebShopApiTests extends TestBase {

    @Test
    @DisplayName("Регистрация пользователя через API")
    void registerUserTest() {
        step("Регистрация пользователя через API", () -> {
            String authCookieValue =
                    given()
                            .log()
                            .uri()
                            .contentType("application/x-www-form-urlencoded")
                            .cookie(requestVerificationToken, requestVerificationTokenValue)
                            .formParam(requestVerificationToken, requestVerificationTokenData)
                            .formParam("Gender", gender)
                            .formParam("FirstName", firstName)
                            .formParam("LastName", lastName)
                            .formParam("Email", email)
                            .formParam("Password", password)
                            .formParam("ConfirmPassword", password)
                            .when()
                            .post("/register")
                            .then()
                            .log().status()
                            .log().body()
                            .statusCode(302)
                            .extract()
                            .cookie(authCookieName);

            open("/Themes/DefaultClean/Content/images/logo.png");
            Cookie authCookie = new Cookie(authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });
        step("Открытие главной страницы и проверка Email созданного пользователя в шапке страницы", () -> {
            open("");
            $(".account").shouldHave(Condition.text(email));
            open("");
        });

        step("Открытие страницы профиля созданного пользователя и проверка его данных", () -> {
            open("/customer/info");
            $("#FirstName").shouldHave(Condition.value(firstName));
            $("#LastName").shouldHave(Condition.value(lastName));
            $("#Email").shouldHave(Condition.value(email));
        });
    }
    @Test
    @DisplayName("Авторизация пользователя через API и редактирование его профиля")
    void editUserWithAuthTest() {
        step("Авторизация созданного пользователя через API и установка cookie в браузер", () -> {
            String authCookieValue = given()
                    .log().all()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("Email", email)
                    .formParam("Password", password)
                    .when()
                    .post("/login")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(302)
                    .extract()
                    .cookie(authCookieName);

            open("/Themes/DefaultClean/Content/images/logo.png");
            Cookie authCookie = new Cookie(authCookieName, authCookieValue);
            WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        });

        step("Открытие страницы профиля пользователя и редактирование его данных", () -> {
            open("/customer/info");
            $("#FirstName").setValue(newFirstName);
            $("#LastName").setValue(newLastName);
            $(byValue(newGender)).click();
            $(".save-customer-info-button").click();
        });

        step("Проверка успешного редактирования данных пользователя", () -> {
            $("#FirstName").shouldHave(value(newFirstName));
            $("#LastName").shouldHave(value(newLastName));
        });
    }
}

