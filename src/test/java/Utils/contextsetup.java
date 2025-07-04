package Utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import Pom.pageobjectmanager;

public class contextsetup {

	public WebDriver driver;
	public TestBase TB;
	public pageobjectmanager POM;
	
	
	
	
	public contextsetup() throws IOException
	{
		TB = new TestBase();
		driver = TB.browserlaunch();
		POM = new pageobjectmanager(driver);
	}
	
	
}
