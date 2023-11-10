package com.caw.reportmanager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.caw.configuration.Config;
import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

import static com.caw.configuration.Constant.*;


public class Report {

    private static Logger LOG = Logger.getLogger(Report.class);

    private static ExtentReports extentReport;
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    private Report(){

    }

    private static void initReports(){
        ExtentSparkReporter spark = new ExtentSparkReporter(Config
                .getProperty("reportPath"));
        extentReport = new ExtentReports();
        extentReport.setSystemInfo("URL",BASE_URL);
        extentReport.attachReporter(spark);
    }

    private static ExtentReports getInstance() {
        if (extentReport == null) {
            initReports();
        }
        return extentReport;
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int)(Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        getInstance().flush();
    }

    public static synchronized ExtentTest startTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        extentTestMap.put((int) (Thread.currentThread().getId()), test);
        return test;
    }
}
