package stepdefinition;

import Utils.ScreenshotUtil;
import stepdefinition.hooks;
import Utils.contextsetup;
import io.cucumber.java.en.*;
import org.testng.Assert;

import static stepdefinition.hooks.scenario; // ✅ Using shared scenario from hooks

public class TC001_Login_step {

    public contextsetup cs;

    public TC001_Login_step(contextsetup cs) {
        this.cs = cs;
    }

    // ✅ Screenshot capture method using hooks.scenario
    private void captureScreenshotAndLog(String stepMessage) {
        String featureName = ScreenshotUtil.getFeatureName().replaceAll(" ", "_");
        String scenarioName = scenario.getName().replaceAll(" ", "_");

        String screenshotPath = ScreenshotUtil.captureScreenshot(cs.driver, featureName, scenarioName); // Already saving file

        // Log screenshot into extent report
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
    public void enter_valid_and(String username, String password) {
        cs.POM.loginscreen().UserName(username);
        cs.POM.lp.Password(password);
        captureScreenshotAndLog("Entered valid username and password");
    }

    @Then("Click on login.")
    public void click_on_login() {
        cs.POM.lp.Login();
        captureScreenshotAndLog("Clicked on login button");
    }
}
