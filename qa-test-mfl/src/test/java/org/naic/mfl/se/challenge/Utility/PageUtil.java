package org.naic.mfl.se.challenge.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageUtil {
    WebDriver driver;

    public PageUtil(WebDriver driver){
        this.driver=driver;
    }

    public void waitForElementVisible(WebElement webElement, int timeOutSeconds){
        WebDriverWait wait=new WebDriverWait(driver,timeOutSeconds,50);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
