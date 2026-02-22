package com.camilocuapio.automation.swaglabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Base {



    private WebDriver driver;

    public Base() {

    }

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver chromeDriverConnection() {
        try {

// Prepare the compatible ChromeDriver
            WebDriverManager.chromedriver().setup();

            // ChromeOptions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);

        } catch (Exception e) {
            e.printStackTrace();
            driver = null; // Avoid NPE if the session fails.
        }
        return driver;
    }






    //Find an element
    public WebElement findElement(By locator) {

        return driver.findElement(locator);
    }

    //Arraylist Collection
    public List<WebElement> findElements(By locator) {

        return driver.findElements(locator);
    }

    //Get text
    public String getText(WebElement element) {

        return element.getText();
    }

    //Get text from an element
    public String getText(By locator) {
        return
                driver.findElement(locator).getText();
    }

    //Write text
    public void type(String inputText, By locator) {

        driver.findElement(locator).sendKeys(inputText);
    }

    //Click the button
    public void click(By locator) {

        driver.findElement(locator).click();
    }

    //The element is displayed
    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    //Visit page
    public void visit(String url) {

        driver.get(url);
    }

    //Get URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
//Dropdown
    public void dropDown(String inputText, By locator){
        Select dropdownZtoA = new Select(findElement(locator));
        dropdownZtoA.selectByVisibleText(inputText);
    }

    // Explicit wait to ensure that the elements are loaded
    public void explicitWaitsCollection(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }



    // Screenshot of each test
    public void takeScreenshot(String name) {
        File screenshotFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(
                    screenshotFile,
                    new File(name + "_" + System.currentTimeMillis() + ".png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImplicitWait(long seconds) {
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(seconds));
    }

}
