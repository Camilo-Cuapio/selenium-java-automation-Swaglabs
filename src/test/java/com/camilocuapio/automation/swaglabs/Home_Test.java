package com.camilocuapio.automation.swaglabs;

import com.sun.tools.javac.Main;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.ClientInfoStatus;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class Home_Test {
    private WebDriver driver;
    HomePage homePage;

    List<String> prueba;

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
   /* @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/

    @Test
    public void TC_04_givenUserIsOnProductsPage_whenProductListIsDisplayed_thenAllProductsAreVisible() {

//Expected product list A to Z
        List<Product> expectedProducts = TestData.getBaseProducts();
        List<Product> actualProducts = homePage.actualNamePriceDescription();

        for (int i = 0; i < expectedProducts.size(); i++) {
            assertEquals(expectedProducts.get(i).getName(),
                    actualProducts.get(i).getName());

            assertEquals(expectedProducts.get(i).getPrice(),
                    actualProducts.get(i).getPrice());

            assertEquals(expectedProducts.get(i).getDescription(),
                    actualProducts.get(i).getDescription());
        }
    }

    @Test
    public void TC_05_givenCurrentProductList_whenComparedWithExpectedList_thenProductsAreInZToAOrder() {
        //Expected product list Z to A
        homePage.dropDownZtoA();
        List<Product> expectedProducts = TestData.getProductsSortedByName(false);
        List<Product> actualProducts = homePage.actualNamePriceDescription();
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void TC_06_givenCurrentPriceList_whenComparedWithExpectedList_thenPricesAreInAscendingOrder() {
        List<String> expectedPrice = new ArrayList<>();

        homePage.dropDownLowToHigh();
        List<Product> expectedProducts = TestData.getProductsSortedByPrice(true);
        List<Product> actualProducts = homePage.actualNamePriceDescription();
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void TC_07_givenCurrentPriceList_whenComparedWithExpectedList_thenPricesAreInDescendingOrder() {

        homePage.dropDownHighToLow();
        List<Product> expectedProducts = TestData.getProductsSortedByPrice(false);
        List<Product> actualProducts = homePage.actualNamePriceDescription();
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void TC_08_givenUserIsOnProductsPage_whenUserAddsAllProductsToCart_thenProductsAreAddedToCart() {

        homePage.btnAddToCart();
        int numAddToCart = homePage.getCartItemCount();
        Assert.assertEquals(numAddToCart, 6);

        int removeCount = homePage.getbtnRemove();
        Assert.assertEquals(removeCount, 6);

    }

}




