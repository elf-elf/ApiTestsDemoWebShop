package com.alexandrova.tests;

import com.github.javafaker.Faker;

public class TestData {

    static Faker faker = new Faker();

    static String authCookieName = "NOPCOMMERCE.AUTH";
    static String firstName = faker.name().firstName();
    static String lastName = faker.name().lastName();
    static String gender = "F";
    static String email = faker.internet().emailAddress();
    static String password = faker.internet().password();
    static String newFirstName = faker.name().firstName();
    static String newLastName = faker.name().lastName();
    static String newGender = "M";

    static String requestVerificationToken = "__RequestVerificationToken";
//    static String requestVerificationTokenValue = "nTH6FXkG2fPKg0sKQZ-b5aSMnKvHmwY8yKqQCwu_T-G_OnBhUolvr4AIrW45zk-" +
//            "US3dH08cUY2JT5tW3bdzaWvWgpHd-idE8Cy6u-DA_7HU1";
//    static String requestVerificationTokenData = "VSMS3cFMlZt3yPxtPy5x1v4osXR4nHDmT8a6ihmv0S9uwJ5u8c1f" +
//            "MKqiKokMSr9aOOq1hoAMEcq7w6x8Te5dzbWdY1zF-EP7kmR7Rn-Z4qQ1";

    static String requestVerificationTokenData = "nTH6FXkG2fPKg0sKQZ-b5aSMnKvHmwY8yKqQCwu_T-G_OnBhUolvr4AIrW45zk-" +
            "US3dH08cUY2JT5tW3bdzaWvWgpHd-idE8Cy6u-DA_7HU1";
    static String requestVerificationTokenValue = "VSMS3cFMlZt3yPxtPy5x1v4osXR4nHDmT8a6ihmv0S9uwJ5u8c1f" +
            "MKqiKokMSr9aOOq1hoAMEcq7w6x8Te5dzbWdY1zF-EP7kmR7Rn-Z4qQ1";


}
