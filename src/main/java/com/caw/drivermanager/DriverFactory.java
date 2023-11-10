package com.caw.drivermanager;

import com.caw.enums.BrowserType;
import com.caw.exceptions.CustomRunTimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.function.Supplier;
import java.util.EnumMap;
import java.util.Objects;

public class DriverFactory {

    private static Logger LOG = LogManager.getLogger(DriverFactory.class);
    private static final Map<BrowserType, Supplier<WebDriver>> MAP = new EnumMap<>(BrowserType.class);
    private static final Supplier<WebDriver> CHROME = com.caw.drivermanager.ChromeBrowser::getDriver;

    static {
        MAP.put(BrowserType.CHROME, CHROME);
    }

    public static WebDriver getDriver(BrowserType browserType){
        if(Objects.isNull(browserType)){
            throw new CustomRunTimeException("please check browser type");
        }else {
            LOG.info(browserType.name()+" launched successfully");
        }
        return MAP.get(browserType).get();
    }
}
