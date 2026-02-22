package com.camilocuapio.automation.swaglabs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Home_Test {
    private WebDriver driver;
    HomePage homePage;

    @Before
    public void setUp() {
        Base base = new Base();
        driver = base.chromeDriverConnection();
        if (driver != null) {


            // Creamos la página de login
            SignInPage signInPage = new SignInPage(driver);
            signInPage.visit("https://www.saucedemo.com/");
            signInPage.login(); // LOGIN explícito
            // Ahora sí podemos ir a HomePage


            homePage = new HomePage(driver);
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

//Expected product list A to Z
        List<String> expectedProducts = new ArrayList<>();
        expectedProducts.add("Test.allTheThings() T-Shirt (Red)");
        expectedProducts.add("Sauce Labs Backpack");
        expectedProducts.add("Sauce Labs Bike Light");
        expectedProducts.add("Sauce Labs Bolt T-Shirt");
        expectedProducts.add("Sauce Labs Fleece Jacket");
        expectedProducts.add("Sauce Labs Onesie");

        //sortProductListInAscendingOrder
        Collections.sort(expectedProducts);
//Validate expected product list against actual
        assertEquals(expectedProducts, homePage.currentProductsName());
    }

    @Test
    public void TC_05_givenCurrentProductList_whenComparedWithExpectedList_thenProductsAreInZToAOrder() {
        //Expected product list Z to A
        List<String> expectedProductsZtoA = new ArrayList<>();
        expectedProductsZtoA.add("Sauce Labs Bike Light");
        expectedProductsZtoA.add("Sauce Labs Backpack");
        expectedProductsZtoA.add("Test.allTheThings() T-Shirt (Red)");
        expectedProductsZtoA.add("Sauce Labs Onesie");
        expectedProductsZtoA.add("Sauce Labs Fleece Jacket");
        expectedProductsZtoA.add("Sauce Labs Bolt T-Shirt");

        Collections.sort(expectedProductsZtoA,Collections.reverseOrder());
        homePage.dropDownZtoA();
//Validate expected product list against actual
        assertEquals(expectedProductsZtoA, homePage.currentProductsName());
        System.out.println(homePage.currentProductsName());
        System.out.println(expectedProductsZtoA);
    }
}
