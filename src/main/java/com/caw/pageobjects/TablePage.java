package com.caw.pageobjects;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.caw.reportmanager.Report;
import com.caw.utility.ScreenShot;
import com.caw.utility.SeleniumActions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TablePage {

    private static final Logger Log = Logger.getLogger(TablePage.class);

    private final WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By TEST_DATA_SUMMARY_BTN = By.xpath("//*[normalize-space(text())='Table Data']//ancestor-or-self::summary");
    private static final By JSON_INPUT_AREA = By.id("jsondata");
    private static final By REFRESH_TABLE_BTN = By.id("refreshtable");
    private static final By DYNAMIC_TABLE_ROWS = By.cssSelector("table[id='dynamictable']>tr");

    public TablePage clickOnSummaryButton(){
        Log.info("Click on Table data button");
        SeleniumActions.clickElement(TEST_DATA_SUMMARY_BTN,driver,2);
        Report.getTest().info("Click on Table data button");
        return this;
    }

    public TablePage enterJsonData(String json){
        Log.info("Enter json as : "+json);
        SeleniumActions.sendKeys(JSON_INPUT_AREA,driver,2,json);
        Report.getTest().info("Enter json as : "+json);
        return this;
    }

    public TablePage clickRefreshTableButton(){
        Log.info("Click on refresh table button");
        SeleniumActions.clickElement(REFRESH_TABLE_BTN,driver,2);
        Report.getTest().info("Click on refresh table button");
        Report.getTest().info(MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShot.addScreenshot(driver)).build());
        return this;
    }

    public List<List<String>> fetchTableData(){
        Log.info("Fetching data from web page");
        List<List<String>> tabledata = new ArrayList<>();
        List<WebElement> element = driver.findElements(DYNAMIC_TABLE_ROWS);
        element.stream().skip(1).forEach(ele -> {
            List<String> rowdata=new ArrayList<>();
            List<WebElement> element1 = ele.findElements(By.tagName("td"));
            element1.forEach(cell -> {
                String text = cell.getText();
                rowdata.add(text);
            });
            tabledata.add(rowdata);
        });
        return tabledata;
    }
}
