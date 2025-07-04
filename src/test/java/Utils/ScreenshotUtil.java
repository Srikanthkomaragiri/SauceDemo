package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import io.cucumber.java.Scenario;
import stepdefinition.hooks;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String featureName, String scenarioName) {
    	String timestamp = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss").format(new Date());
    	String screenshotDir = System.getProperty("user.dir") + "/screenshots/" + featureName + "/" + scenarioName;
        new File(screenshotDir).mkdirs();

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        String path = screenshotDir+"/"  + "Screenshot_" + System.currentTimeMillis() +timestamp+ ".png";
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    // ✅ Always extract feature name from currently running scenario in hooks
    public static String getFeatureName() {
        try {
            String rawUri = hooks.scenario.getUri().toString();
            return new File(rawUri).getName().replace(".feature", "");
        } catch (Exception e) {
            return "UnknownFeature";
        }
    }
}
