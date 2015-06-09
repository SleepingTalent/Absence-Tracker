package com.noveria.cukes.helpers.selenium.page.helper;

import org.openqa.selenium.By;

public class FindByHelper {

    public static By findById(String id) {
        return By.id(id);
    }

    public static By findByClassAndText(String cssClass, String text) {
        return By.xpath("//*[contains(@class,'"+cssClass+"')][normalize-space(text())='"+text+"']");
    }

    public static By findByIdAndClass(String id, String cssClass) {
        return By.xpath("//*[(@id='"+id+"') and contains(@class,'"+cssClass+"')]");
    }

    public static By findByIdAndAttributeValue(String id, String attributeName, String attributeValue) {
        return By.xpath("//*[(@id='"+id+"') and (@"+attributeName+"='"+attributeValue+"')");
    }

    public static By findByClass(String cssClass) {
        return By.className(cssClass);
    }

    public static By findByLinkText(String linkText) {
        return By.linkText(linkText);
    }

    public static By findTableDataWithText(String text) {
       return By.xpath("//td[text()='"+text+"']");
    }

    public static By findTableRowWithText(String text) {
        return By.xpath("//tr[td//text()[contains(., '"+text+"')]]");
    }
}
