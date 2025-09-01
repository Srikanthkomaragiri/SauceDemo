package Cucumberoptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import io.qameta.allure.testng.AllureTestNg;

@CucumberOptions(
    features = "classpath:feature/TC001_Login.feature", // ✅ correct path
    glue = {"stepdefinition"},
    monochrome = true,
    tags = "@LOGIN",
    		plugin = {
    			    "pretty",
    			    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
    			    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    			}
)
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
 // ✅ needed for TestNG integration
public class TC001_Login_TestRunner extends AbstractTestNGCucumberTests {}
