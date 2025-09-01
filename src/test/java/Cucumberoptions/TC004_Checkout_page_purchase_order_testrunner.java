package Cucumberoptions;

import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
(
	features = "classpath:feature/TC004_Checkout_page_purchase_order.feature",
	glue =  {"stepdefinition"},
	monochrome = true,
			plugin = {
				    "pretty",
				    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				},
	tags = "@OrderPlaced"
		
		
		
		
		)



@Listeners({io.qameta.allure.testng.AllureTestNg.class})

public class TC004_Checkout_page_purchase_order_TestRunner extends AbstractTestNGCucumberTests {

}
