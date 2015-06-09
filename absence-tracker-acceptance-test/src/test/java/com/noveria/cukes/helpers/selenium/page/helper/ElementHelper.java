package com.noveria.cukes.helpers.selenium.page.helper;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class ElementHelper {

    private static final long WAIT_TIME = 10;

    CucumberWebDriver driver;

    WebDriverWait webDriverWait;

    public ElementHelper(CucumberWebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIME);
    }

    public WebElement findElementById(String id, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findById(id)), reThrowError);
    }

    public WebElement findElementByClass(String cssClass, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findByClass(cssClass)), reThrowError);
    }


    public List<WebElement> findElementsByClass(String className, boolean reThrowError) {
        List<WebElement> webElements = new ArrayList<WebElement>();
        try {
            webElements = driver.findElements(FindByHelper.findByClass(className));
        } catch (Exception ex) {
            handleTimeout(ex, reThrowError);
        }

        return webElements;
    }

    public WebElement findByIdAndClass(String id, String cssClass, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findByIdAndClass(id,cssClass)), reThrowError);
    }

    public WebElement findByIdAndAttribute(String id, String attributeName, String attributeValue, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findByIdAndAttributeValue(id,attributeName,attributeValue)), reThrowError);
    }

    public WebElement findByLinkText(String linkText, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findByLinkText(linkText)), reThrowError);
    }

    public WebElement findElementsByClassContainingText(String cssClass,String text, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findByClassAndText(cssClass,text)), reThrowError);
    }

    public WebElement findTableDataWithText(String text, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findTableDataWithText(text)), reThrowError);
    }

    public WebElement findTableRowWithText(String text, boolean reThrowError) {
        return waitUntilConditionReturnElement(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findTableRowWithText(text)), reThrowError);
    }

    private WebElement waitUntilConditionReturnElement(ExpectedCondition<WebElement> condition, boolean reThrowError) {
        WebElement webElement = null;

        try {
            webElement = webDriverWait.until(condition);
        } catch (Exception ex) {
            handleTimeout(ex, reThrowError);
        }

        return webElement;
    }

    private void handleTimeout(Exception ex, boolean reThrowError) {
        if (reThrowError) {
            throw new SeleniumTimeoutException();
        } else {
            fail(ex.getMessage());
        }
    }

}
