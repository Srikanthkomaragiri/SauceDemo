package Cucumberoptions;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
		
		features = "classpath:feature/TC002_Addproductsintocart.feature",
		glue = {"stepdefinition"},
		monochrome = true,
				plugin = {
					    "pretty",
					    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
					    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					},
	    tags = "@ADDPRODUCT"
		
		
		
		
		)



@Listeners({io.qameta.allure.testng.AllureTestNg.class})

public class TC002_Addproductsintocart_TestRunner extends AbstractTestNGCucumberTests {

	
}
