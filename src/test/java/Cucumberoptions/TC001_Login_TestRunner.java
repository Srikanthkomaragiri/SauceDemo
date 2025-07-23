package Cucumberoptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/feature/TC001_Login.feature",
    glue = {"stepdefinition"},
    tags = "@LOGIN",
    monochrome = true,
    plugin = {
        "pretty"
    }
)
public class TC001_Login_TestRunner extends AbstractTestNGCucumberTests {

   
}
