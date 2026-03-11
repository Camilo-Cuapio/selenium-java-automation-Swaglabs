package com.camilocuapio.automation.swaglabs;

import com.sun.tools.javac.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.ClientInfoStatus;
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

        Collections.sort(expectedProductsZtoA, Collections.reverseOrder());
        homePage.dropDownZtoA();
//Validate expected product list against actual
        assertEquals(expectedProductsZtoA, homePage.currentProductsName());
        System.out.println(homePage.currentProductsName());
        System.out.println(expectedProductsZtoA);
    }

    @Test
    public void TC_06_givenCurrentPriceList_whenComparedWithExpectedList_thenPricesAreInAscendingOrder() {
        List<String> expectedPrice = new ArrayList<>();

        expectedPrice.add("$7.99");
        expectedPrice.add("$9.99");
        expectedPrice.add("$15.99");
        expectedPrice.add("$15.99");
        expectedPrice.add("$29.99");
        expectedPrice.add("$49.99");

        homePage.dropDownLowToHigh();
        System.out.println("actuale" + homePage.currentPrice());
        System.out.println("esperado" + expectedPrice);

        assertEquals(expectedPrice, homePage.currentPrice());
    }

    @Test
    public void TC_07_givenCurrentPriceList_whenComparedWithExpectedList_thenPricesAreInDescendingOrder() {
        List<String> expectedPrice = new ArrayList<>();
        expectedPrice.add("$49.99");
        expectedPrice.add("$29.99");
        expectedPrice.add("$15.99");
        expectedPrice.add("$15.99");
        expectedPrice.add("$9.99");
        expectedPrice.add("$7.99");

        homePage.dropDownHighToLow();
        assertEquals(expectedPrice, homePage.currentPrice());

        System.out.println("actual" + homePage.currentPrice());
        System.out.println("expected" + expectedPrice);
    }

    @Test
    public void TC_05_namePriceDescription() {

        List<Product> expectedNamePriceDescription = new ArrayList<>();

        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Backpack",
                "$29.99",
                "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Bike Light",
                "$9.99",
                "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Bolt T-Shirt",
                "$15.99",
                "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Fleece Jacket",
                "$49.99",
                "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office."
        ));
        expectedNamePriceDescription.add(new Product(
                "Sauce Labs Onesie",
                "$7.99",
                "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."
        ));
        expectedNamePriceDescription.add(new Product(
                "Test.allTheThings() T-Shirt (Red)",
                "$15.99",
                "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton."
        ));

       List<Product> expectedProducts=expectedNamePriceDescription;

       List<Product> actualProducts=homePage.actualNamePriceDescription();

        for (int i = 0; i < expectedProducts.size(); i++) {

            assertEquals(expectedProducts.get(i).getName(),
                    actualProducts.get(i).getName());

            assertEquals(expectedProducts.get(i).getPrice(),
                    actualProducts.get(i).getPrice());

            assertEquals(expectedProducts.get(i).getDescription(),
                    actualProducts.get(i).getDescription());
        }
    }


}
