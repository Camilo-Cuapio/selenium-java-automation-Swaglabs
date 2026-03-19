package com.camilocuapio.automation.swaglabs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@ExtendWith(ScreenshotOnFailureExtension.class)
public class SignInTest extends BaseTest {

    SignInPage signInPage;

    @BeforeEach
    public void setUp() {
        signInPage = new SignInPage(driver); // ✅ usa el de BaseTest
        signInPage.visit("https://www.saucedemo.com/");
    }

    @Test
    public void TC_01_givenValidCredentials_whenUserLogsIn_thenAccessIsGranted() {
        signInPage.login();
        Assertions.assertTrue(signInPage.pageAccess());
    }

    @Test
    public void TC_02_givenIncorrectPassword_whenUserAttemptsToLogIn_thenErrorMessageIsDisplayed() {
        signInPage.loginError();
        Assertions.assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                signInPage.loginErrorMessage()
        );
    }

    @Test
    public void TC_03_givenCredentialsWithIncorrectPassword_whenUserAttemptsToLogIn_thenErrorIconsAreDisplayed() {
        signInPage.loginError();
        Assertions.assertEquals(1, signInPage.xBtnUser());
        Assertions.assertEquals(1, signInPage.xBtnPassword());
        Assertions.assertEquals(1, signInPage.xBtnMsjError());
    }
}
