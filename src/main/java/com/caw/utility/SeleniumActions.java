package com.caw.utility;

import com.caw.configuration.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumActions {

    private static final Logger Log = LogManager.getLogger(SeleniumActions.class);

    private SeleniumActions(){

    }

    public static void launchURL(WebDriver driver, String URL){
        driver.manage().window().maximize();
        Log.info("Window Maximized");
        driver.get(URL);
        Log.info(URL+" launched");
    }

    public static void clickElement(By by,
                                    WebDriver driver,
                                    long waitInSecs){
        WaitStrategy.waitUntilElementToBePresent(by,driver,waitInSecs);
        WaitStrategy.waitUntilElementToBeVisible(by,driver,waitInSecs);
        WaitStrategy.waitUntilElementToBeClickable(by,driver,waitInSecs);
        driver.findElement(by).click();
    }

    public static void sendKeys(By by,
                                WebDriver driver,
                                long waitInSecs,
                                String input){
        WaitStrategy.waitUntilElementToBePresent(by,driver,waitInSecs);
        WaitStrategy.waitUntilElementToBeVisible(by,driver,waitInSecs);
        WaitStrategy.waitUntilElementToBeClickable(by,driver,waitInSecs);
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(input);
    }
}
