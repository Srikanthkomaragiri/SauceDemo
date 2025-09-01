package stepdefinition;

import java.io.File;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.AllureAttachments;
import Utils.ExtentReportManager;
import Utils.LogManagerUtil;
import Utils.ScreenshotUtil;
import Utils.contextsetup;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class hooks {

    public static Scenario scenario;
    public contextsetup cs;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ThreadLocal<ExtentReports> extentReport = new ThreadLocal<>();
    public static ThreadLocal<Logger> loggerThread = new ThreadLocal<>();

    public hooks(contextsetup cs) {
        this.cs = cs;
    }

    // 🔷 Before Scenario — Set up logging, extent, and context
    @Before(order = 1)
    public void beforeScenario(Scenario sc) {
        System.out.println("Scenario injection and setup");
        scenario = sc;

        String featureName = ScreenshotUtil.getFeatureName();

        // Extent Report Setup
        ExtentReports report = ExtentReportManager.getReporter(featureName);
        ExtentTest test = report.createTest(scenario.getName());
        extentReport.set(report);
        extentTest.set(test);

        // Log4j Setup
        Logger logger = LogManagerUtil.getLogger(featureName);
        logger.info("===== Starting Scenario: " + scenario.getName() + " =====");
        loggerThread.set(logger);
    }

    // 🔷 After Scenario — Attach screenshot on failure, flush extent
    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed() && cs.driver != null) {
            AllureAttachments.attachScreenshot(cs.driver, "Failure - " + scenario.getName());
            AllureAttachments.attachPageSource(cs.driver);
            AllureAttachments.attachLogs("Scenario failed: " + scenario.getName());

            Logger logger = loggerThread.get();
            if (logger != null) {
                logger.error("❌ Scenario FAILED: " + scenario.getName());
            }
        }

        if (extentReport.get() != null) {
            extentReport.get().flush();
        }
    }

    // 🔷 After Every Step — Attach Allure screenshot + log
    @AfterStep
    public void afterEachStep(Scenario scenario) {
        WebDriver driver = cs.driver;
        if (driver != null) {
            AllureAttachments.attachScreenshot(driver, scenario.getName());
            AllureAttachments.attachLogs("Step completed in: " + scenario.getName());

            Logger logger = loggerThread.get();
            if (logger != null) {
                logger.info("✅ Step completed in scenario: " + scenario.getName());
            }
        }
    }

    // 🔷 After All — Close browser
    @After
    public void teardown() {
        if (cs.driver != null) {
            cs.driver.quit();
            System.out.println("🧹 Browser closed.");
        }
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
