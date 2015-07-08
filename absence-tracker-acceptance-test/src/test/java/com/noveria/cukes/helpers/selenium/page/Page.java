package com.noveria.cukes.helpers.selenium.page;


import com.noveria.cukes.helpers.selenium.page.helper.PageHelper;
import com.noveria.cukes.helpers.selenium.page.helper.SeleniumTimeoutException;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class Page {

    public static final String MESSAGES_CONTAINER = "messages_container";
    private PageHelper pageHelper;
    protected CucumberWebDriver webDriver;

    protected Page(CucumberWebDriver webDriver) {
        this.webDriver = webDriver;
        pageHelper = new PageHelper(webDriver);
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    protected void sendKeysToId(String id, String text) {
        try {
            getPageHelper().findElementById(id,true).sendKeys(text);
        } catch (SeleniumTimeoutException ste) {
            getPageHelper().findElementById(id).sendKeys(text);
        }
    }

    protected void assertValidationErrorDisplayed(String title, String details) {
        WebElement element = getPageHelper().findElementById(MESSAGES_CONTAINER);
        assertEquals(title + "\n" + details, element.getText());
    }

}
