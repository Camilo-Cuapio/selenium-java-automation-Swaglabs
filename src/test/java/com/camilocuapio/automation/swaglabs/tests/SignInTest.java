package com.camilocuapio.automation.swaglabs.tests;

import com.camilocuapio.automation.swaglabs.base.BaseTest;
import com.camilocuapio.automation.swaglabs.pages.SignInPage;
import com.camilocuapio.automation.swaglabs.utils.ScreenshotOnFailureExtension;
import io.qameta.allure.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;


@Epic("Swag Labs Automation")
@Feature("Sign In Page Products")
@ExtendWith(ScreenshotOnFailureExtension.class)

public class SignInTest extends BaseTest {

    SignInPage signInPage;

    @BeforeEach
    public void setUp() {
        signInPage = new SignInPage(driver); // usa el de BaseTest
        signInPage.visit("https://www.saucedemo.com/");
    }

    @Test
    @DisplayName("Login with valid credentials")
    @Description("Verify that user can log in successfully with valid credentials")
    @Feature("Authentication")
    @Story("User login")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_01_givenValidCredentials_whenUserLogsIn_thenAccessIsGranted() {
        signInPage.login();
        Assertions.assertTrue(signInPage.pageAccess());
    }

    @Test
    @DisplayName("Login with valid credentials ERRORFORCAPTURE")
    @Description("Verify that user can log in successfully with valid credentials")
    @Feature("Authentication")
    @Story("User login")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_ERRORFORCAPTURE_givenValidCredentials_whenUserLogsIn_thenAccessIsGranted() {
        signInPage.loginError();
        Assertions.assertTrue(signInPage.pageAccess());
    }

    @Test
    @DisplayName("Incorrect password login")
    @Description("Verify that the user cannot log in with an incorrect password and display an error message")
    @Feature("Authentication")
    @Story("User login")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_02_givenIncorrectPassword_whenUserAttemptsToLogIn_thenErrorMessageIsDisplayed() {
        signInPage.loginError();
        Assertions.assertEquals(
                "Epic sadface: Username and password do not match any user in this service",
                signInPage.loginErrorMessage()
        );
    }

    @Test
    @DisplayName("Display of X buttons")
    @Description("Verify that when an incorrect password is entered, the X buttons are displayed for username, password, and error message")
    @Feature("Authentication")
    @Story("User login")
    @Severity(SeverityLevel.CRITICAL)
    public void TC_03_givenCredentialsWithIncorrectPassword_whenUserAttemptsToLogIn_thenErrorIconsAreDisplayed() {
        signInPage.loginError();
        Assertions.assertEquals(1, signInPage.xBtnUser());
        Assertions.assertEquals(1, signInPage.xBtnPassword());
        Assertions.assertEquals(1, signInPage.xBtnMsjError());
    }
}
