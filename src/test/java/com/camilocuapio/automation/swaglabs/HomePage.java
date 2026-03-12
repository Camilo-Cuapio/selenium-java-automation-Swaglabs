package com.camilocuapio.automation.swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends Base {

    //locate Dropdown
    By dropdownelement = By.cssSelector(".product_sort_container");
    //Locate products from current list
    By listNameElement = By.cssSelector(".inventory_item_name");
    //locate price list item
    By listPriceElement = By.cssSelector(".inventory_item_price");
    //locate product description elements
    By listDescriptionElement = By.cssSelector(".inventory_item_desc");
    //locate shopping cart element
    By shoppingCartElement = By.cssSelector(".shopping_cart_link");
//Btn add to cart
    By addToCartElement=By.cssSelector("#add-to-cart-sauce-labs-backpack");



    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<String> currentDescription() {
        explicitWaitsCollection(listDescriptionElement);
        List<WebElement> currentDescriptionElement = findElements(listDescriptionElement);
        List<String> currentDescription = new ArrayList<>();
        for (WebElement description : currentDescriptionElement) {
            currentDescription.add(description.getText().trim());
        }
        return currentDescription;
    }

    //click On Dropdown And Select Z To A Option
    public void dropDownZtoA() {
        dropDown("Name (Z to A)", dropdownelement);

    }

    //click on dropdown and select low to high option
    public void dropDownLowToHigh() {
        dropDown("Price (low to high)", dropdownelement);
    }

    public void dropDownHighToLow() {
        dropDown("Price (high to low)", dropdownelement);
    }

    public List<Product> actualNamePriceDescription() {
        List<WebElement> actualNameElement = findElements(listNameElement);
        List<WebElement> actualPriceElement = findElements(listPriceElement);
        List<WebElement> actualDescriptionElement = findElements(listDescriptionElement);

        int size = Math.min(actualNameElement.size(), Math.min(actualPriceElement.size(), actualDescriptionElement.size()));
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < actualNameElement.size(); i++) {
            products.add(new Product(
                            actualNameElement.get(i).getText(),
                            actualPriceElement.get(i).getText(),
                            actualDescriptionElement.get(i).getText()
                    )
            );
        }
        return products;
    }

    public int addProductToCart(){
        click(addToCartElement);
        String count = findElement(shoppingCartElement).getText();
        return Integer.parseInt(count);
    }
}


