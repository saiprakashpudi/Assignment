package com.caw.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitStrategy {


    public static void waitUntilElementToBePresent(By by,
                                                   WebDriver driver,
                                                   long waitInSecs){
        new WebDriverWait(driver, Duration.ofSeconds(waitInSecs))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .pollingEvery(Duration.ofMillis(250))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitUntilElementToBeVisible(By by,
                                                   WebDriver driver,
                                                   long waitInSecs){
        new WebDriverWait(driver, Duration.ofSeconds(waitInSecs))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .pollingEvery(Duration.ofMillis(250))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitUntilElementToBeClickable(By by,
                                                   WebDriver driver,
                                                   long waitInSecs){
        new WebDriverWait(driver, Duration.ofSeconds(waitInSecs))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .pollingEvery(Duration.ofMillis(250))
                .until(ExpectedConditions.elementToBeClickable(by));
    }
}
