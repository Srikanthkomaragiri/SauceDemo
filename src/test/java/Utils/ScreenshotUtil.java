package Utils;

import org.apache.commons.io.FileUtils; // Utility class for file operations (copying files)
import org.openqa.selenium.*; // For WebDriver and TakesScreenshot
import io.cucumber.java.Scenario; // For accessing Cucumber scenario details
import stepdefinition.hooks; // To access current scenario object

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    //  Captures a screenshot and returns the file path
    public static String captureScreenshot(WebDriver driver, String featureName, String scenarioName) {
        
        //  Create a timestamp for the filename
        String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss").format(new Date());
        
        //  Create the folder structure: /screenshots/FeatureName/ScenarioName
        String screenshotDir = System.getProperty("user.dir") + "/screenshots/" + featureName + "/" + scenarioName;
        
        //  Ensure the directory exists
        new File(screenshotDir).mkdirs();

        //  Take screenshot as file using Selenium
        //Takescreenshot is an interface using driver we implement the methods
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        //  Build the full path for saving the screenshot
        String path = screenshotDir + "/" + "Screenshot_" + System.currentTimeMillis() + timestamp + ".png";
        
        try {
            //  Save the screenshot to the destination path
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            //  If saving fails, print the error
            e.printStackTrace();
        }

        //  Return the final screenshot path
        return path;
    }

    //  Extract the current feature file name from the Scenario object
    public static String getFeatureName() {
        try {
            //  scenario object from hooks (Thread-safe access)
            String rawUri = hooks.scenario.getUri().toString();

            //  Get only the file name (e.g., "Login.feature" → "Login")
            return new File(rawUri).getName().replace(".feature", "");
        } catch (Exception e) {
            //  In case of failure, return default
            return "UnknownFeature";
        }
    }
}
