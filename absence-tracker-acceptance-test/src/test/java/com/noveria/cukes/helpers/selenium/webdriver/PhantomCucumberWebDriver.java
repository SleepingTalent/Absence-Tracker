package com.noveria.cukes.helpers.selenium.webdriver;

import cucumber.api.Scenario;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;

public class PhantomCucumberWebDriver extends PhantomJSDriver implements CucumberWebDriver {


    public PhantomCucumberWebDriver() {
        super(getJSCapabilities());
    }

    private static Capabilities getJSCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();

        ArrayList<String> cliArgsCap = new ArrayList<String>();
        cliArgsCap.add("--ignore-ssl-errors=true");
        cliArgsCap.add("--disk-cache=true");
        cliArgsCap.add("--max-disk-cache-size=256");
        cliArgsCap.add("--proxy-type=none");
        cliArgsCap.add("--web-security=false");

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,cliArgsCap);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "loadimages",true);
        capabilities.setCapability("takesScreenshot", true);

        final String phantomDriverPath = "./phantom-1.9.7/phantomjs";

        final String absolutePath = PhantomCucumberWebDriver.class.getClassLoader().getResource(phantomDriverPath).getPath();

        final File phantomDriverFile = new File(absolutePath);
        if(phantomDriverFile.exists() && !phantomDriverFile.canExecute()) {
            phantomDriverFile.setExecutable(true);
        }

        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,absolutePath);

        return capabilities;
    }

    public void takeScreenShot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) this).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public void closeBrowser() {
        quit();
    }
}
