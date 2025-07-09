package stepdefinition;

import java.io.File;

import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Utils.ExtentReportManager;
import Utils.LogManagerUtil;
import Utils.contextsetup;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class hooks {

    public static Scenario scenario; // 🔁 Make static to access across steps
    public contextsetup cs;

    
    
   /* Purpose:
    	To store the report object for the current feature file — one report per feature.

    	🔹 Why ThreadLocal?
    	Because if two features are executed in parallel, they each need their own ExtentReports object — so the reports don’t conflict or overwrite.
   */
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

   
    
    
   // Extent reports code
    @Before
    //this is the entry point of the extent reports.
    public void beforeScenario(Scenario scenario) {
        //this calls next
    	String featureName = getFeatureName(scenario);//here we passed object of the SCenarioimpl class
       
    	
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
//Extent reports
    private String getFeatureName(Scenario scenario) {
        String raw = scenario.getUri().toString(); // For Cucumber 7+//here by using scenario interface existing methods we 
                                                  //can get the current running feature path.
        return new File(raw).getName().replace(".feature", "");//receive the feature name without feature extension.
    }
  
    
    
    
    //log4j logs code
    @Before
    public void beforeScenariolog4j(Scenario scenario) {
        String featureName = getFeatureName(scenario); // your utility method
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
