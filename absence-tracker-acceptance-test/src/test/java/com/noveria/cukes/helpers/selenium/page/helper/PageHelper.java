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
        return findElementById(id,false);
    }

    public WebElement findElementById(String id,boolean reThrow) {
        return elementHelper.findElementById(id,reThrow);
    }

    public WebElement findElementByClass(String className) {
        return elementHelper.findElementByClass(className, false);
    }

    public List<WebElement> findElementsByClass(String className) {
        return elementHelper.findElementsByClass(className, false);
    }

    public WebElement findTableDataWithText(String text) {
            return elementHelper.findTableDataWithText(text, false);
    }

    public WebElement findTableDataWithText(String text, boolean reThrow) {
        return elementHelper.findTableDataWithText(text, reThrow);
    }

    public WebElement findByLinkWithinDivById(String id, boolean reThrow) {
        return elementHelper.findByLinkWithinDivById(id, reThrow);
    }

    public WebElement findTableRowWithText(String text, boolean reThrow) {
        return elementHelper.findTableRowWithText(text, reThrow);
    }

    public WebElement findTableRowWithText(String text) {
        return elementHelper.findTableRowWithText(text, false);
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

    public void moveToAndClickElement(WebElement element) {
        new Actions(webDriver).moveToElement(element).click().perform();
    }

    public WebElement assertMenuDisplayed(String labelText, boolean reThrow) {
        return findLinkByText(labelText, reThrow);
    }

    public WebElement findLinkByText(String text, boolean reThrow) {
            return elementHelper.findByLinkText(text, reThrow);
    }

    public WebElement findLinkByText(String text) {
        return elementHelper.findByLinkText(text, false);
    }

    public WebElement findSelectElement(String id, String value) {
        return elementHelper.findSelectElement(id,value, false);
    }

    public WebElement findElementByClassAndText(String cssClass, String text) {
        return elementHelper.findElementByClassAndText(cssClass,text,false);
    }

}
