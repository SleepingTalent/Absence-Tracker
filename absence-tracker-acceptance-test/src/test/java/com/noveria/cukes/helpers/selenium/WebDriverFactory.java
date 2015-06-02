package com.noveria.cukes.helpers.selenium;


import com.noveria.cukes.helpers.selenium.webdriver.ChromeCucumberWebDriver;
import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;
import com.noveria.cukes.helpers.selenium.webdriver.FirefoxCucumberWebDriver;
import com.noveria.cukes.helpers.selenium.webdriver.PhantomCucumberWebDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public final class WebDriverFactory {

    public enum Browser {
        FIREFOX, PHANTOM, CHROME;
    }

    public static CucumberWebDriver getWebDriver() {

        Browser browserType = getBrowser();
        CucumberWebDriver webDriver = null;

            switch (browserType) {
                case FIREFOX:
                    webDriver = new FirefoxCucumberWebDriver();
                    break;
                case PHANTOM:
                    webDriver = new PhantomCucumberWebDriver();
                    break;
                case CHROME:
                    webDriver = new ChromeCucumberWebDriver();
            }

            webDriver.getWindowHandle();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return webDriver;
    }

    private static Browser getBrowser() {
        String browserType = System.getProperty("browser");

        if("FIREFOX".equalsIgnoreCase(browserType)) {
           return Browser.FIREFOX;
        }else if ("PHANTOM".equalsIgnoreCase(browserType)) {
           return Browser.PHANTOM;
        }else if ("CHROME".equalsIgnoreCase(browserType)) {
            return Browser.CHROME;
        }else {
            fail("Web Browser not supported : "+browserType);
            return null;
        }

    }
}
