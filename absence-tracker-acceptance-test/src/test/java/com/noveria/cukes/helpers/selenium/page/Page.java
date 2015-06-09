package com.noveria.cukes.helpers.selenium.page;


import com.noveria.cukes.helpers.selenium.page.helper.PageHelper;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class Page {

    public static final String MESSAGES_CONTAINER = "messages_container";
    private PageHelper pageHelper;

    protected Page(CucumberWebDriver webDriver) {
        pageHelper = new PageHelper(webDriver);
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    protected void sendKeysToId(String id, String text) {
        try {
            getPageHelper().findElementById(id).sendKeys(text);
        } catch (StaleElementReferenceException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            getPageHelper().findElementById(id).sendKeys(text);
        }
    }

    protected void assertValidationErrorDisplayed(String title, String details) {
        WebElement element = getPageHelper().findElementById(MESSAGES_CONTAINER);
        assertEquals(title + "\n" + details, element.getText());
    }

    private WebElement assertMenuDisplayed(String labelText) {
        return getPageHelper().assertMenuDisplayed(labelText);
    }
}
