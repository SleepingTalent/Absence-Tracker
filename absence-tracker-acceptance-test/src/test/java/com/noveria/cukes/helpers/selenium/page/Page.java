package com.noveria.cukes.helpers.selenium.page;


import com.noveria.cukes.helpers.selenium.webdriver.CucumberWebDriver;

public class Page {

    private PageHelper pageHelper;

    protected Page(CucumberWebDriver webDriver) {
        pageHelper = new PageHelper(webDriver);
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

}
