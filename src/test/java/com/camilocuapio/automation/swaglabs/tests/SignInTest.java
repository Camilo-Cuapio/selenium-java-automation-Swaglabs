package com.camilocuapio.automation.swaglabs.tests;
import com.camilocuapio.automation.swaglabs.base.BaseTest;
import com.camilocuapio.automation.swaglabs.pages.SignInPage;
import com.camilocuapio.automation.swaglabs.utils.ScreenshotOnFailureExtension;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;


@Epic("Swag Labs Automation")
@Feature("Home Page Products")
@ExtendWith({ScreenshotOnFailureExtension.class, io.qameta.allure.junit5.AllureJunit5.class})
//@ExtendWith(ScreenshotOnFailureExtension.class)
public class SignInTest extends BaseTest {

    SignInPage signInPage;

    @BeforeEach
    public void setUp() {
        signInPage = new SignInPage(driver); // usa el de BaseTest
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
