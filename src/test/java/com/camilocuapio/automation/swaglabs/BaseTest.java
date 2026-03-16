package com.camilocuapio.automation.swaglabs;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    //Enter page
    @Before
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
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
