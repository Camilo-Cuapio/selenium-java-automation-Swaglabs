package com.camilocuapio.automation.swaglabs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignIn_Test {
    private WebDriver driver;
    SignInPage signInPage;

    /// /Enter page
    @Before
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
    @After
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
        assertTrue(signInPage.pageAccess());
        signInPage.takeScreenshot("sigin"); //Captura pantalla
    }

    //Iniciar sesion contraseña incorrecta y validar mensaje de error
    @Test
    public void test2() throws InterruptedException {
        signInPage.loginError();
        assertEquals("Epic sadface: Username and password do not match any user in this service", signInPage.mensajeErrorLogeo());
        //iniciarSesionPagina.takeScreenshot("Contraseña incorrecta, Mensaje de error"); //captura pantalla
    }


}

