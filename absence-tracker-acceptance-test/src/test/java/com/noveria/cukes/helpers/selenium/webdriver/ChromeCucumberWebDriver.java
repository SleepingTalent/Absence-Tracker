package com.noveria.cukes.helpers.selenium.webdriver;

import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeCucumberWebDriver extends ChromeDriver implements CucumberWebDriver {

    public void takeScreenShot(Scenario scenario) {
    }

    public void closeBrowser() {
        close();
    }
}
