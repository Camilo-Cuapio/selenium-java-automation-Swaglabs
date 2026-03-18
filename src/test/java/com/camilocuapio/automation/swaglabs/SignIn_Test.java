package com.camilocuapio.automation.swaglabs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;



public class SignIn_Test {
    private WebDriver driver;
    SignInPage signInPage;

    //Enter page
    @BeforeEach
    public void setUp() {
        Base base = new Base();
        driver = base.chromeDriverConnection();
        if (driver != null) {
            signInPage = new SignInPage(driver);
            signInPage.visit("https://www.saucedemo.com/");
        } else {
            throw new RuntimeException("The WebDriver could not be started. Check your Chrome version.");
        }
    }

    //Close page
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Log in with correct credentials
    @Test
    public void TC_01_givenValidCredentials_whenUserLogsIn_thenAccessIsGranted() throws InterruptedException {
        signInPage.login();
        Thread.sleep(2000);
        Assertions.assertTrue(signInPage.pageAccess());
        //signInPage.takeScreenshot("TC_01_givenValidCredentials_whenUserLogsIn_thenAccessIsGranted"); //Captura pantalla
    }


    @Test
    public void TC_02_givenIncorrectPassword_whenUserAttemptsToLogIn_thenErrorMessageIsDisplayed() throws InterruptedException {
        signInPage.loginError();
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service", signInPage.loginErrorMessage());
        //iniciarSesionPagina.takeScreenshot("TC_02_givenIncorrectPassword_whenUserAttemptsToLogIn_thenErrorMessageIsDisplayed"); //captura pantalla
    }

    @Test
    public void TC_03_givenCredentialsWithIncorrectPassword_whenUserAttemptsToLogIn_thenErrorIconsAreDisplayedInUsernameAndPasswordFieldsAndErrorMessageIsShown() {
        signInPage.loginError();
        Assertions.assertEquals(1, signInPage.xBtnUser());
        Assertions.assertEquals(1, signInPage.xBtnPassword());
        Assertions.assertEquals(1, signInPage.xBtnMsjError());
        //iniciarSesionPagina.takeScreenshot("Validate that the “X” button is displayed.");    //Captura pantalla
    }

}

