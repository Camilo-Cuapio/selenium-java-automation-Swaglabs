package com.camilocuapio.automation.swaglabs.utils;

import com.camilocuapio.automation.swaglabs.base.BaseTest;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {

        if (context.getExecutionException().isPresent()) {
            try {
                Object testInstance = context.getRequiredTestInstance();

                if (testInstance instanceof BaseTest) {
                    WebDriver driver = ((BaseTest) testInstance).getDriver();

                    if (driver != null) {
                        byte[] screenshot = ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);

                        Allure.addAttachment(
                                "Failure Screenshot",
                                "image/png",
                                new ByteArrayInputStream(screenshot),
                                ".png"
                        );
                    }
                }
            } catch (Exception e) {
                System.out.println("Screenshot failed: " + e.getMessage());
            }
        }
    }
}