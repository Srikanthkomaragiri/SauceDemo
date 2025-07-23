package stepdefinition;

import java.io.File;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.devtools.v135.page.model.Screenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportManager;
import Utils.LogManagerUtil;
import Utils.ScreenshotUtil;
import Utils.contextsetup;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class hooks {

    public static Scenario scenario; // You're making the current scenario accessible everywhere in your framework, by storing it here: Make static to access across steps
    public contextsetup cs;

    
    
   /* Purpose:
    	To store the report object for the current feature file — one report per feature.

    	 Why ThreadLocal?
    	Because if two features are executed in parallel, they each need their own ExtentReports object — so the reports don’t conflict or overwrite.
   */
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ThreadLocal<ExtentReports> extentReport = new ThreadLocal<>();
    
    public hooks(contextsetup cs) {
        this.cs = cs;
    }

    
 /*------------------------------------
  
Scenario is an interface provided by Cucumber.
When you use it as a parameter in a @Before or @After hook, 
Cucumber will automatically pass the currently running scenario to that method.
use it in utilities like screenshots, logs, reports ,etc.
    
----------------------------------------
 */   @Before
    public void setScenario(Scenario sc) {
        System.out.println("get the scenario interface injection here through DI");
        scenario = sc;
    }

   
    
    
   // Extent reports code
    @Before
    //this is the entry point of the extent reports.
    public void Extentreportscreate(Scenario scenario) {
        //this calls next
    	String featureName = ScreenshotUtil.getFeatureName();//here we passed object of the SCenarioimpl class
       
    	
    	// is a class and will create the report in a specific path using the feature name.
    	ExtentReports report = ExtentReportManager.getReporter(featureName);
       
    	
    	
    	//ExtentTest is used to log each scenario from the feature file into that report.
    	//It doesn't “execute” the scenario — it just records what happens in it.
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
//extent reports
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    
    
    
    //log4j logs code
    @Before
    public void log4jreports(Scenario scenario) {
        String featureName = ExtentReportManager.getFeatureName(); // your utility method
        Logger logger = LogManagerUtil.getLogger(featureName);
        scenario.log("Logger initialized for " + featureName);
        logger.info("Starting Scenario: " + scenario.getName());

        // Optional: Store logger in ThreadLocal if needed
    

    
}
    
    
    @After
    public void teardown() {
        if (cs.driver != null) {
            cs.driver.quit();
            System.out.println("Browser closed after feature.");
        }
    }
}
