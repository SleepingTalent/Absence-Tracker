package com.noveria.cukes.helpers.selenium.webdriver;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FirefoxCucumberWebDriver extends FirefoxDriver implements CucumberWebDriver {

    public void takeScreenShot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) this).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public void closeBrowser() {
        quit();
    }
}
