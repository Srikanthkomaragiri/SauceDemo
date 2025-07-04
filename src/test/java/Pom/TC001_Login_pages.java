package Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TC001_Login_pages {

	public WebDriver driver;
	
	public TC001_Login_pages(WebDriver driver)
	{
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//div[@class=\"form_group\"]//input[@id=\"user-name\"]")
	WebElement USERNAME;
	
	
	@FindBy(xpath = "//input[@id=\"password\"]")
	WebElement PASSWORD;
	
	@FindBy(xpath = "//input[@id=\"login-button\"]")
    WebElement LOGIN;
	
	public void UserName(String un)
	{
		USERNAME.sendKeys(un);
	}
	
	
	
	public void Password(String Pass)
	{
		PASSWORD.sendKeys(Pass);
	}


   public void Login()
   {
	   
	   LOGIN.click();
   }

}
