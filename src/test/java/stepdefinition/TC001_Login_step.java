package stepdefinition;

import Utils.AllureAttachments;
import Utils.LogManagerUtil;
import Utils.ScreenshotUtil;
import Utils.contextsetup;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import org.testng.Assert;
import org.apache.logging.log4j.Logger;

import static stepdefinition.hooks.scenario;

import java.io.FileInputStream;
import java.io.IOException;

public class TC001_Login_step {

    public contextsetup cs;
    Logger logger;

    public TC001_Login_step(contextsetup cs) {
        this.cs = cs;

        // Automatically pick feature name from screenshot path helper
        String featureName = ScreenshotUtil.getFeatureName().replaceAll(" ", "_");
        logger = LogManagerUtil.getLogger(featureName);
    }

    //  Screenshot + extent report logging
    private void captureScreenshotAndLog(String stepMessage) {
        String featureName = ScreenshotUtil.getFeatureName().replaceAll(" ", "_");
        String scenarioName = scenario.getName().replaceAll(" ", "_");

        String screenshotPath = ScreenshotUtil.captureScreenshot(cs.driver, featureName, scenarioName);

        try {
            hooks.getTest().info(stepMessage,
                com.aventstack.extentreports.MediaEntityBuilder
                    .createScreenCaptureFromPath(screenshotPath)
                    .build()
            );
        } catch (Exception e) {
            hooks.getTest().warning("Could not attach screenshot: " + e.getMessage());
        }

        
    } 
    
    
 

    
    
    

    
    @Given("Log in to the Website.")
    public void log_in_to_the_website() {
        System.out.println("------------Browser launched from stepdefinition.");
        Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        captureScreenshotAndLog("User launched the login page");
    }

    @When("Enter valid {string} and {string}")
    public void enter_valid_and(String string, String string2) {
        cs.POM.loginscreen().UserName(string);
        cs.POM.lp.Password(string2);
        captureScreenshotAndLog("Entered valid username and password");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("User clicks login button")
    @Then("Click on login.")
    public void click_on_login() {
        Allure.step("Clicked login button");
        cs.POM.lp.Login();
        captureScreenshotAndLog("Clicked on login button");
    }
}
