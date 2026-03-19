package com.camilocuapio.automation.swaglabs;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;


@ExtendWith(ScreenshotOnFailureExtension.class)
public class HomeTest extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    public void setupPage() {
        homePage = new HomePage(driver);
    }

    @Test
    public void TC_04_givenUserIsOnProductsPage_whenProductListIsDisplayed_thenAllProductsAreVisible() {

//Expected product list A to Z
        List<Product> expectedProducts = TestData.getBaseProducts();
        List<Product> actualProducts = homePage.actualNamePriceDescription();

        for (int i = 0; i < expectedProducts.size(); i++) {
            Assertions.assertEquals(expectedProducts.get(i).getName(),
                    actualProducts.get(i).getName());

            Assertions.assertEquals(expectedProducts.get(i).getPrice(),
                    actualProducts.get(i).getPrice());

            Assertions.assertEquals(expectedProducts.get(i).getDescription(),
                    actualProducts.get(i).getDescription());
        }
    }

    @Test
    public void TC_05_givenCurrentProductList_whenComparedWithExpectedList_thenProductsAreInZToAOrder() {

        //Expected product list Z to A
        homePage.dropDownZtoA();
        List<Product> expectedProducts = TestData.getProductsSortedByName(false);
        List<Product> actualProducts = homePage.actualNamePriceDescription();
        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void TC_06_givenCurrentPriceList_whenComparedWithExpectedList_thenPricesAreInAscendingOrder() {

        List<String> expectedPrice = new ArrayList<>();

        homePage.dropDownLowToHigh();
        List<Product> expectedProducts = TestData.getProductsSortedByPrice(true);
        List<Product> actualProducts = homePage.actualNamePriceDescription();
        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void TC_07_givenCurrentPriceList_whenComparedWithExpectedList_thenPricesAreInDescendingOrder() {

        homePage.dropDownHighToLow();
        List<Product> expectedProducts = TestData.getProductsSortedByPrice(false);
        List<Product> actualProducts = homePage.actualNamePriceDescription();
        Assertions.assertEquals(expectedProducts, actualProducts);
    }

    @Test
    public void TC_08_givenUserOnProductsPage_whenUserAddsAndRemovesAllProducts_thenCartCountIsSixAndThenZero() {
//
        List<WebElement> buttonsAdd = homePage.getbtnAddToCart();
        int countAdd = 0;
        for (int i = 0; i < buttonsAdd.size(); i++) {
            buttonsAdd.get(i).click();
            countAdd++;
        }
        Assertions.assertEquals(homePage.getCartItemCount(), countAdd);
//
        int removeCount = homePage.getbtnRemove().size();
        Assertions.assertEquals(6, removeCount);

//
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        while (homePage.getbtnRemove().size() > 0) {
            WebElement btn = homePage.getbtnRemove().get(0);
            btn.click();
            wait.until(ExpectedConditions.stalenessOf(btn));
        }
        Assertions.assertEquals(0, homePage.getbtnRemove().size());
    }

    @Test
    public void TC_09_shouldKeepOnlyOneProductInCartAfterAddingThreeAndRemovingTwo() {
        List<WebElement> buttons = homePage.getbtnAddToCart();

        int[] indexes = {0, 2, 5};
        int expectedCount = indexes.length;

        for (int index : indexes) {
            buttons.get(index).click();
        }

        Assertions.assertEquals(homePage.getCartItemCount(), expectedCount);
        Assertions.assertEquals(homePage.getbtnRemove().size(), expectedCount);

        homePage.getbtnRemove().get(0).click();
        homePage.getbtnRemove().get(1).click();

        Assertions.assertEquals(homePage.getCartItemCount(), expectedCount - 2);
        Assertions.assertEquals(homePage.getbtnRemove().size(), expectedCount - 2);
        }

    @Test
    void TC_FAIL_intentionalError() {
        // Esto fallará a propósito
        Assertions.assertEquals(1, 2, "Este test fallará intencionalmente");
    }
    }






