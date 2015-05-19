package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import sun.nio.cs.ThreadLocalCoders;

import static org.junit.Assert.assertEquals;

public class WelcomePage extends Page {

    private static final String NAME_INPUT_ID = "welcomeForm:nameInput";
    private static final String WELCOME_BTN_ID = "welcomeForm:welcomeBtn";
    private static final String WELCOME_NAME = "welcomeForm:welcomeName";

    public WelcomePage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void inputName(String name) {
        getPageHelper().findElementById(NAME_INPUT_ID).sendKeys(name);
    }

    public void clickWelcomeButton() {
        getPageHelper().findElementById(WELCOME_BTN_ID).click();
    }

    public void assertNameDisplayed(String expectedName) {
        String actualName = "";

            try {
                actualName = getPageHelper().findElementById(WELCOME_NAME).getText();
            } catch(StaleElementReferenceException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                actualName = getPageHelper().findElementById(WELCOME_NAME).getText();
            }

        assertEquals(expectedName, actualName);
    }
}
