package TestRunner;

import org.testng.annotations.Test;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)- This is only for Junit not for TestNG
@CucumberOptions(
    features = "src/test/java/Feature",     // where your .feature file is
    glue = {"stepdifinition"},                     // where your step def methods live
    plugin = {"pretty"},                          // for readable output
    monochrome = true
    
    
)

public class TestRunner extends AbstractTestNGCucumberTests {

	@Test(priority = 1)
	public void runloginfeature()
	{
		
		System.setProperty("cucumber.filter.tags", "@LOGIN");
		//super.runScenario("Login Feature");
		
		}
	
	@Test(priority = 2, dependsOnMethods = "runloginfeature")
	public void runAddproduct()
	{
		System.setProperty("cucumber.feature.tags","@ADDPRODUCT");
	}
	
}
