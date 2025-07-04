package stepdefinition;

import static stepdefinition.hooks.scenario;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import Utils.ScreenshotUtil;
import Utils.contextsetup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class TC004_Checkout_page_purchase_order_step {
	
	public String FN,LN,ZC;
	public contextsetup cs;
	public TC004_Checkout_page_purchase_order_step (contextsetup cs)
	{
		
	this.cs = cs;
		
	}
	
	 private void capturescreenshotadd() {
	        String FeatureName = ScreenshotUtil.getFeatureName().replaceAll(" ", "_");
	        String ScenarioName = scenario.getName().replaceAll(" ", "_");
	        ScreenshotUtil.captureScreenshot(cs.driver, FeatureName, ScenarioName);
	    }
	
	
	@Given("Click on the Checkout button")
	public void click_on_the_checkout_button() {
      
	cs.POM.checkoutorderplaced().checkout_button();
	
	// Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
    capturescreenshotadd();

	}

	@Given("User Navigated to the Checkout page")
	public void user_navigated_to_the_checkout_page() {

    System.out.println("++User currently on the checkout page");
    
 // Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
    capturescreenshotadd();
	}

	@When("User Enetered the following valid checkout information details")
	public void user_enetered_the_following_valid_checkout_information_details(DataTable details) 
	{
     
            List <Map<String, String>> datalist = details.asMaps();
            for(Map<String, String> data : datalist)
  {
            	
            	
            String FN =	data.get("First Name");
            String LN = data.get("Last Name");
            String ZC =	data.get("Zip code");

            	
            	
	System.out.println(FN);
	System.out.println(LN);
	System.out.println(ZC);
	
	cs.POM.checkoutorderplaced().firstnamecheck(FN);
	cs.POM.checkoutorderplaced().lastnamecheck(LN);
	cs.POM.checkoutorderplaced().zipcodecheck(ZC);
  }
                        
         // Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
	        capturescreenshotadd();                  
		
	}

	@When("User click on continue")
	public void user_click_on_continue() {
	  
		cs.POM.checkoutorderplaced().continue_check();
		// Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        capturescreenshotadd();
		
	}

	@When("User verified the user details before payment")
	public void user_verified_the_user_details_before_payment() {

		System.out.println("Details correctly enetered and displayed");
		Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        capturescreenshotadd();
		
	}

	@Then("User clicked on FInish and Order placed successfully")
	public void user_clicked_on_f_inish_and_order_placed_successfully() {
	  
		cs.POM.checkoutorderplaced().Finish_Check();
		// Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        capturescreenshotadd();
		
	}

	@Then("User click on Back to home and navigated back to homepage")
	public void user_click_on_back_to_home_and_navigated_back_to_homepage() {
	 
		cs.POM.checkoutorderplaced().back_home();
		// Assert.assertTrue(cs.driver.getTitle().contains("Swag Labs"));
        capturescreenshotadd();
	}



	
	
	

}
