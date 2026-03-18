package com.camilocuapio.automation.swaglabs;

import io.qameta.allure.Allure;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {
    protected WebDriver driver;

    //Enter page
    @BeforeEach
    public void setUp() {
        Base base = new Base();
        driver = base.chromeDriverConnection();

        if (driver != null) {
            SignInPage signInPage = new SignInPage(driver);
            signInPage.visit("https://www.saucedemo.com/");
            signInPage.login();
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
//Taking screenshots with Allure Reports
    public void takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        // aquí vamos a intentar detectar fallo
    }
}
