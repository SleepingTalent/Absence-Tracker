package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

import static org.junit.Assert.assertEquals;

public class WelcomePage extends Page {

    private static final String NAME_INPUT_ID = "nameInput";
    private static final String WELCOME_BTN_ID = "welcomeBtn";
    private static final String WELCOME_NAME = "welcomeName";

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
        String actualName = getPageHelper().findElementById(WELCOME_NAME).getText();
        assertEquals(expectedName, actualName);
    }
}
