package com.camilocuapio.automation.swaglabs.utils;

import com.camilocuapio.automation.swaglabs.base.BaseTest;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        try {
            Object testInstance = context.getRequiredTestInstance();

            if (testInstance instanceof BaseTest) {
                WebDriver driver = ((BaseTest) testInstance).getDriver();

                if (driver != null) {
                    byte[] screenshot = ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.BYTES);

                    Allure.addAttachment(
                            "Failure Screenshot",
                            new ByteArrayInputStream(screenshot)
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}

