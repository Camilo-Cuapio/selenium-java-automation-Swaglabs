package com.camilocuapio.automation.swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SignInPage extends Base {


    //Item locator
    By userLocator = By.xpath("//*[@id=\"user-name\"]");
    By passwordLocator = By.cssSelector("#password");
    By loginBtnLocator = By.cssSelector(".submit-button");
    //Mensaje de error, contraseña incorrecta
    By loginErrorMessage = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
    //Localizadores de X
    By xUser = By.cssSelector("#login_button_container>div>form>div:nth-child(1)>svg");
    By xPassword = By.cssSelector("#login_button_container>div>form>div:nth-child(2)>svg");
    By xMsjError = By.cssSelector(".error-message-container>h3>button>svg");
    //Localizar de lista actual productos
    By listProductElement = By.cssSelector(".inventory_item_name");

    public SignInPage(WebDriver driver) {
        super(driver);
    }
//Log in with correct username and password

    public void login() {
        if (isDisplayed(userLocator)) {
            type("standard_user", userLocator);
            type("secret_sauce", passwordLocator);
            click(loginBtnLocator);
        } else {
            System.out.println("The login was not completed correctly");
        }
    }

    public Boolean pageAccess() {
        return getCurrentUrl().contains("inventory.html");
    }

    //Log in with incorrect password

    public void loginError() {
        if (isDisplayed(userLocator)) {
            type("standard_user", userLocator);
            type("secret_sauc", passwordLocator);
            click(loginBtnLocator);
        } else {
            System.out.println("The login was not completed correctly");
        }
    }

    public String loginErrorMessage() {

        return getText(loginErrorMessage);

    }

    //Validation of button X in Username, Password and Error Message

    public int xBtnUser() {
        List<WebElement> xUsu = findElements(xUser);
        return xUsu.size();
    }

    public int xBtnPassword() {
        List<WebElement> xPass = findElements(xPassword);
        return xPass.size();
    }

    public int xBtnMsjError() {
        List<WebElement> xMsjErrorr = findElements(xMsjError);
        return xMsjErrorr.size();
    }
    //Get a list of current products

    public List<String> currentProducts() {

        // Explicit wait to ensure that the elements are loaded
        esperasExplicitasColle(listProductElement);

        // Capture the elements
        List<WebElement> actualProductoselement = findElements(listProductElement);
        List<String> actualProductos = new ArrayList<>();

        for (WebElement element : actualProductoselement) {
            actualProductos.add(element.getText().trim());//trim clean spaces
        }
        return actualProductos;
    }
}
