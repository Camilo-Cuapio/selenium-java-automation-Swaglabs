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

    //Locate products from current list
    By listProductElement = By.cssSelector(".inventory_item_name");
    //locate Dropdown
    By dropdownZtoAelement = By.cssSelector(".product_sort_container");
    //product List In Z To A Order
    By listProductElementZtoA = By.cssSelector(".inventory_item_name");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //current list of products
    public List<String> currentProductsNameAtoZ() {
        // Explicit wait to ensure that the elements are loaded
        explicitWaitsCollection(listProductElement);

        // Capture the elements
        List<WebElement> currentProductelementAtoZ = findElements(listProductElement);
        List<String> currentProduct = new ArrayList<>();

        for (WebElement element : currentProductelementAtoZ) {
            currentProduct.add(element.getText().trim());//trim clean spaces
        }
        return currentProduct;
    }

    public void dropDownZtoA() {
        //click On Dropdown And Select Z  ToA Option
        Select dropdownZtoA = new Select(findElement(dropdownZtoAelement));
        dropdownZtoA.selectByVisibleText("Name (Z to A)");

    }

    public List<String> currentProductsZtoA() {


        explicitWaitsCollection(listProductElementZtoA);

        List<WebElement> currentProductelementZtoA = findElements(listProductElementZtoA);
        List<String> currentProcductZtoA = new ArrayList<>();

        for (WebElement elementZtoA : currentProductelementZtoA) {
            currentProcductZtoA.add(elementZtoA.getText().trim());
        }
        return currentProcductZtoA;

    }

}
