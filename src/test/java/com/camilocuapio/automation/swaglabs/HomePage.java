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


    public HomePage(WebDriver driver) {
        super(driver);
    }

    //current list of products
    public List<String> currentProductsName() {
        // Explicit wait to ensure that the elements are loaded
        explicitWaitsCollection(listNameElement);

        // Capture the elements
        List<WebElement> currentProductelement = findElements(listNameElement);
        List<String> currentProduct = new ArrayList<>();

        for (WebElement element : currentProductelement) {
            currentProduct.add(element.getText().trim());//trim clean spaces
        }
        return currentProduct;
    }

    //prie list
    public List<String> currentPrice() {
        explicitWaitsCollection(listPriceElement);
        List<WebElement> priceListElement = findElements(listPriceElement);
        List<String> priceList = new ArrayList<>();
        for (WebElement price : priceListElement) {
            priceList.add(price.getText().trim());
        }
        return priceList;
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

    public List<Product> namePriceDescription() {
        List<WebElement> actualNameElement = findElements(listNameElement);
        List<WebElement> actualPriceElement = findElements(listPriceElement);
        List<WebElement> actualDescriptionElement = findElements(listDescriptionElement);

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
}


