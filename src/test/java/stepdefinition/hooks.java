package stepdefinition;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportManager;
import Utils.contextsetup;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class hooks {

    public static Scenario scenario; // 🔁 Make static to access across steps
    public contextsetup cs;

    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ThreadLocal<ExtentReports> extentReport = new ThreadLocal<>();
    
    public hooks(contextsetup cs) {
        this.cs = cs;
    }

    @Before
    public void setScenario(Scenario sc) {
        System.out.println("Before hook: Browser already launched.");
        scenario = sc;
    }

   
    
    
    
    @Before
    public void beforeScenario(Scenario scenario) {
        String featureName = getFeatureName(scenario);
        ExtentReports report = ExtentReportManager.getReporter(featureName);
        ExtentTest test = report.createTest(scenario.getName());

        extentTest.set(test);
        extentReport.set(report);
    }

    @After
    public void afterScenario() {
        if (extentReport.get() != null) {
            extentReport.get().flush();
        }
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    private String getFeatureName(Scenario scenario) {
        String raw = scenario.getUri().toString(); // For Cucumber 7+
        return new File(raw).getName().replace(".feature", "");
    }
  
    
    
    @After
    public void teardown() {
        if (cs.driver != null) {
            cs.driver.quit();
            System.out.println("Browser closed after feature.");
        }
    }
}
