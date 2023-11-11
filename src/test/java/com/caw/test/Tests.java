package com.caw.test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.caw.drivermanager.Driver;
import com.caw.enums.BrowserType;
import com.caw.pageobjects.TablePage;
import com.caw.reportmanager.Report;
import com.caw.utility.ScreenShot;
import com.caw.utility.SeleniumActions;
import com.caw.utility.Utility;
import lombok.SneakyThrows;
import org.apache.log4j.PropertyConfigurator;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.List;

import static com.caw.configuration.Constant.BASE_URL;

public class Tests {

    @BeforeSuite
    public void beforesuite(){
        PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("log4j2.properties"));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethod(Method method){
        //Initializing the browser
        Driver.initDriver(BrowserType.CHROME);
        //Make test node entry in report
        Report.startTest(method.getName());
    }

    @SneakyThrows
    @Test(dataProvider = "testData")
    public void validateTable(String inputData){
        WebDriver driver = Driver.getDriver();
        //launch url
        SeleniumActions.launchURL(driver,BASE_URL);
        //page actions
        List<List<String>> actualTable = new TablePage(driver)
                .clickOnSummaryButton()
                .enterJsonData(inputData)
                .clickRefreshTableButton()
                .fetchTableData();
        //assert actual and expected
        List<List<String>> expectedTable = new Utility().convertLisOfMapsToListOfLists(inputData);
        Assertions.assertThat(actualTable)
                .containsExactlyInAnyOrderElementsOf(expectedTable);
        Report.getTest().pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenShot.addScreenshot(driver),
                MarkupHelper.createLabel("Test Passed Successfully", ExtentColor.GREEN).getMarkup()).build());
    }

    @DataProvider
    public Object[] testData(){
        String inputData = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\n" +
                "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\n" +
                "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]\n";

        return new Object[]{inputData};
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser(){
        Driver.quiteDriver();
        Report.endTest();
    }

}
