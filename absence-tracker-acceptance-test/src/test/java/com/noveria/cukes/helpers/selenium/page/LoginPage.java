package com.noveria.cukes.helpers.selenium.page;

import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import com.noveria.cukes.runtime.RuntimeState;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class LoginPage extends Page {

    private static final String USERNAME_INPUT_ID = "userName";
    private static final String PASSWORD_INPUT_ID = "password";
    private static final String LOGIN_BTN_ID = "loginBtn";
    private static final String MAIN_PANEL_ID  = "mainPanel";

    public LoginPage(CucumberWebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToLoginPage() {
        getPageHelper().navigateTo(RuntimeState.ABSENCE_TRACKER_URL);
        assertPagePresent();
    }

    public void assertPagePresent(){
        getPageHelper().findElementById(MAIN_PANEL_ID);

    }

    public void inputUserName(String username) {
        sendKeysToId(USERNAME_INPUT_ID,username);
    }

    public void inputPassword(String password) {
        sendKeysToId(PASSWORD_INPUT_ID,password);
    }

    public void clickLoginButton() {
        getPageHelper().findElementById(LOGIN_BTN_ID).click();
    }

    public void assertLoginErrorIsDisplayed() {
        assertValidationErrorDisplayed("Login Unsuccessful","Bad credentials");
    }
}
