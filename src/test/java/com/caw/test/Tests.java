package com.caw.test;

import com.caw.drivermanager.Driver;
import com.caw.enums.BrowserType;
import com.caw.pageobjects.TablePage;
import com.caw.pojos.TableData;
import com.caw.pojos.TableDataList;
import com.caw.reportmanager.Report;
import com.caw.utility.SeleniumActions;
import com.caw.utility.Utility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.caw.configuration.Constant.BASE_URL;

public class Tests {


    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethod(Method method){
        //Initializing the browser
        Driver.initDriver(BrowserType.CHROME);
        //Make test node entry in report
        Report.startTest(method.getName());
    }

    @SneakyThrows
    @Test
    public void validateTable(){
        String inputData = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\n" +
                "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\n" +
                "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]\n";

        WebDriver driver = Driver.getDriver();
        //launch url
        SeleniumActions.launchURL(driver,BASE_URL);

        List<List<String>> actualTable = new TablePage(driver)
                .clickOnSummaryButton()
                .enterJsonData(inputData)
                .clickRefreshTableButton()
                .fetchTableData();
        //assert actual and expected
        List<List<String>> expectedTable = new Utility().convertLisOfMapsToListOfLists(inputData);
        Assertions.assertThat(actualTable)
                .containsExactlyInAnyOrderElementsOf(expectedTable);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser(){
        Driver.quiteDriver();
        Report.endTest();
    }

}
