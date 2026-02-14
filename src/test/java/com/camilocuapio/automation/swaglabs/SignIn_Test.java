package com.camilocuapio.automation.swaglabs;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

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
}

