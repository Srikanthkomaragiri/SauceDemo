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

	 static {
	        // Set output PDF report path dynamically for this feature
	        String featureName = "ADDPRODUCT";  // or extract from file if dynamic
	        System.setProperty("extent.reporter.pdf.out",
	                System.getProperty("user.dir") + "/target/ExtentReport/" + featureName + "/ExtentReport.pdf");

	        // Optional: also customize Spark (HTML) output if needed
	        System.setProperty("extent.reporter.spark.out",
	                System.getProperty("user.dir") + "/target/ExtentReport/" + featureName + "/ExtentReport.html");
	    }
}
