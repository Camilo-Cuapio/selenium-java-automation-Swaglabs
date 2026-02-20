package com.camilocuapio.automation.swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Base {

    //Locate products from current list
    By listProductElement = By.cssSelector(".inventory_item_name");



    public HomePage(WebDriver driver){
        super(driver);
    }

    //current list of products
    public List<String> currentProducts(){
        // Explicit wait to ensure that the elements are loaded
        explicitWaitsCollection(listProductElement);

        // Capture the elements
        List<WebElement> currentProductelement = findElements(listProductElement);
        List<String> currentProduct = new ArrayList<>();

        for (WebElement element : currentProductelement) {
            currentProduct.add(element.getText().trim());//trim clean spaces
        }
        return currentProduct;
    }

}
