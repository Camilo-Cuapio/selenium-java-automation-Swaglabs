package com.camilocuapio.automation.swaglabs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class Home_Test {
    private WebDriver driver;
    HomePage homePage;

    @Before
    public void setUp(){
        Base base=new Base();
        driver=base.chromeDriverConnection();
        if (driver !=null){
            homePage=new HomePage(driver);
            homePage.visit("https://www.saucedemo.com/");
        }else {
            throw new RuntimeException("The WebDriver could not be started. Check your Chrome version.");
        }
    }
    //Close page
    @After
    public void tearDown(){
        if (driver !=null){
            driver.quit();
        }
    }
}
