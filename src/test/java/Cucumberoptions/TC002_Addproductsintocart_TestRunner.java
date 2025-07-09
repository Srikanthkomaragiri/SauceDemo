package Cucumberoptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		
		features = "src/test/java/Feature/TC002_Addproductsintocart.feature",
		glue = "stepdefinition",
		monochrome = true,
	   plugin = { "pretty",
					    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					},
	    tags = "@ADDPRODUCT"
		
		
		
		
		)




public class TC002_Addproductsintocart_TestRunner extends AbstractTestNGCucumberTests {

	
}
