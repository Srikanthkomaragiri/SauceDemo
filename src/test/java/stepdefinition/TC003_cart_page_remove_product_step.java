package stepdefinition;

import static stepdefinition.hooks.scenario;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.invokers.AbstractParallelWorker.Arguments;

import Utils.ScreenshotUtil;
import Utils.contextsetup;
import io.cucumber.core.gherkin.Argument;
import io.cucumber.java.en.*;

public class TC003_cart_page_remove_product_step {
	
	
	public contextsetup cs;
	public TC003_cart_page_remove_product_step(contextsetup cs)
	{
		this.cs = cs;
	}
	
	 private void capturescreenshotadd() {
	        String FeatureName = ScreenshotUtil.getFeatureName().replaceAll(" ", "_");
	        String ScenarioName = scenario.getName().replaceAll(" ", "_");
	        ScreenshotUtil.captureScreenshot(cs.driver, FeatureName, ScenarioName);
	    }

	
	@Given("user navigated to the cart page")
	public void user_navigated_to_the_cart_page() {
	
		cs.POM.Cartpageremove().carticon();
		System.out.println("++usr is currently view cart page.");
		// Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
	        capturescreenshotadd();
	}

	@When("User removed one of the Added Product")
	public void user_removed_one_of_the_added_product() {

		cs.POM.Cartpageremove().removetshirt();
		// Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
	        capturescreenshotadd();
		
	}

	@Then("Product was successfully removed")
	public void product_was_successfully_removed() {

   System.out.println("++user sucessfully removed the product");
   Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
   capturescreenshotadd();
		
	}




}
