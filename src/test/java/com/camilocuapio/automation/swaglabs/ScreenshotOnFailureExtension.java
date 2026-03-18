package com.camilocuapio.automation.swaglabs;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.Optional;

public class ScreenshotOnFailureExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();

        // Solo si la clase del test extiende BaseTest
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                TakesScreenshot ts = (TakesScreenshot) driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(
                        context.getDisplayName() + " - failed screenshot",
                        new ByteArrayInputStream(screenshot)
                );
            }
        }
    }

    // Opcional: log para tests exitosos
    @Override
    public void testSuccessful(ExtensionContext context) {
        // No hacemos nada, solo capturamos fallos
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) { }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) { }
}
