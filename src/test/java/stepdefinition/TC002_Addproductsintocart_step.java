package stepdefinition;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Utils.ScreenshotUtil;
import Utils.contextsetup;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import static stepdefinition.hooks.scenario;

public class TC002_Addproductsintocart_step {

    public contextsetup cs;    
    public WebDriver driver;
   

    public TC002_Addproductsintocart_step(contextsetup cs) {
        this.cs = cs;
    }

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


    @Given("User should succesfully navigated to the products page.")
    public void user_should_succesfully_navigated_to_the_products_page() {
        System.out.println("User is on the Add products page");
        Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        captureScreenshotAndLog("User should succesfully navigated to the products page.");
    }

    @When("User selcted t-shirt")
    public void user_selcted_t_shirt() {
        cs.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        cs.POM.selectproducts().addproductshirt();
        captureScreenshotAndLog("User selcted t-shirt");    
    }

    @When("User selected bag")
    public void user_selected_bag() {
        cs.POM.selectproducts().addproductbag();
        captureScreenshotAndLog("User selected bag");
    }

    @Then("products sucessfully added into the cart")
    public void products_sucessfully_added_into_the_cart() {
        Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        System.out.println("products added");
        captureScreenshotAndLog("products sucessfully added into the cart");;
    }

     
}
