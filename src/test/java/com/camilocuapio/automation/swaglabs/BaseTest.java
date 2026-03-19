package com.camilocuapio.automation.swaglabs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUpDriver() {
        driver = new Base().chromeDriverConnection();
        if (driver == null) {
            throw new RuntimeException("WebDriver no pudo inicializarse.");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}