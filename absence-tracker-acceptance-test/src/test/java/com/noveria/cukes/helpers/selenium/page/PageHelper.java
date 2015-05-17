package com.noveria.cukes.helpers.selenium.page;


import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import cucumber.api.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageHelper {

    CucumberWebDriver webDriver;

    public PageHelper(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement findElementById(String id) {
        return webDriver.findElement(By.id(id));
    }

    public WebElement findElementByClass(String className) {
        return webDriver.findElement(By.className(className));
    }

    public List<WebElement> findElementsByClass(String className) {
        return webDriver.findElements(By.className(className));
    }

    public void takeScreenShot(Scenario scenario) {
        webDriver.takeScreenShot(scenario);
    }

    public void navigateTo(String url) {
        webDriver.get(url);
    }

}
