package Cucumberoptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
	features = "src/test/java/feature/TC004_Checkout_page_purchase_order.feature",
	glue =  {"stepdefinition"},
	monochrome = true,
			plugin = { "pretty",
				    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
	tags = "@OrderPlaced"
		
		
		
		
		)




public class TC004_Checkout_page_purchase_order_TestRunner extends AbstractTestNGCucumberTests {

}
