package Cucumberoptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		
	features = "src/test/java/Feature/TC003_Cart_page_remove_product.feature",
	glue = "stepdefinition",
	monochrome = true,
	tags  = "@CART_PAGE_REMOVE_PRODUCT",
	  plugin = { "pretty",
			    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
			}
		
		
		
		
		)



public class TC003_Cart_page_remove_product_TestRunner extends AbstractTestNGCucumberTests{

}
