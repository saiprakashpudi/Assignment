package com.caw.drivermanager;

import com.caw.configuration.Config;
import com.caw.enums.BrowserType;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class Driver {

    private static final Logger Log = Logger.getLogger(Driver.class);

    private static ThreadLocal<WebDriver> dr =new ThreadLocal<>();

    private Driver(){

    }

    public static WebDriver getDriver(){
        return dr.get();
    }

    private static void setDriver(WebDriver driver){
        dr.set(driver);
    }

    private static void remove() {
        dr.remove();
    }

    public static void initDriver(BrowserType browserType){
        setDriver(DriverFactory.getDriver(browserType));
    }

    public static void quiteDriver(){
        WebDriver driver = getDriver();
        if(Objects.nonNull(driver)) {
            Log.info("quiting driver...");
            driver.quit();
            remove();
        }
    }

}
