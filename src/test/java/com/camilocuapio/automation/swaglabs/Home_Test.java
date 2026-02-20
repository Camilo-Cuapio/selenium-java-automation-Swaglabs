package com.camilocuapio.automation.swaglabs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Home_Test {
    private WebDriver driver;
    HomePage homePage;

    @Before
    public void setUp() {
        Base base = new Base();
        driver = base.chromeDriverConnection();
        if (driver != null) {
            homePage = new HomePage(driver);
            homePage.visit("https://www.saucedemo.com/");
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

    @Test
    public void TC_04_givenUserIsOnProductsPage_whenProductListIsDisplayed_thenAllProductsAreVisible() {
        List<String> expectedProducts = new ArrayList<>();
        expectedProducts.add("Test.allTheThings() T-Shirt (Red)");
        expectedProducts.add("Sauce Labs Backpack");
        expectedProducts.add("Sauce Labs Bike Light");
        expectedProducts.add("Sauce Labs Bolt T-Shirt");
        expectedProducts.add("Sauce Labs Fleece Jacket");
        expectedProducts.add("Sauce Labs Onesie");

        Collections.sort(expectedProducts);


    }
}
