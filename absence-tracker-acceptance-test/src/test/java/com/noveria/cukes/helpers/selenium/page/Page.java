package com.noveria.cukes.helpers.selenium.page;


import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.StaleElementReferenceException;

public class Page {

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
        } catch(StaleElementReferenceException e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            getPageHelper().findElementById(id).sendKeys(text);
        }
    }

}
