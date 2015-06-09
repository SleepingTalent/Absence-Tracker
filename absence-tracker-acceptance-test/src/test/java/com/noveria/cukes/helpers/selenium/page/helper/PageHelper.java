package com.noveria.cukes.helpers.selenium.page.helper;


import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import cucumber.api.Scenario;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class PageHelper {

    CucumberWebDriver webDriver;

    ElementHelper elementHelper;

    public PageHelper(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
        elementHelper = new ElementHelper(webDriver);
    }

    public WebElement findElementById(String id) {
        return elementHelper.findElementById(id,false);
    }

    public WebElement findElementByClass(String className) {
        return elementHelper.findElementByClass(className, false);
    }

    public List<WebElement> findElementsByClass(String className) {
        return elementHelper.findElementsByClass(className, false);
    }

    public void takeScreenShot(Scenario scenario) {
        webDriver.takeScreenShot(scenario);
    }

    public void navigateTo(String url) {
        webDriver.get(url);
    }

    public void moveToElement(WebElement element) {
        new Actions(webDriver).moveToElement(element).perform();
    }

    public WebElement assertMenuDisplayed(String labelText) {
        return findLinkByText(labelText);
    }

    public WebElement findLinkByText(String text) {
            return elementHelper.findByLinkText(text, true);
    }

}
