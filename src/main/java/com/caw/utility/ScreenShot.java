package com.caw.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {

    private ScreenShot(){

    }

    //capture base64 image
    public static String addScreenshot(WebDriver driver){
        String screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        return "data:image/png;base64,"+screenshotAs;
    }
}
