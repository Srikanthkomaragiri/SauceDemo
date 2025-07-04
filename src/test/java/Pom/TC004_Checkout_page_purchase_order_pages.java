package Pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC004_Checkout_page_purchase_order_pages {
	
	
	public WebDriver driver;
	
	public TC004_Checkout_page_purchase_order_pages(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(xpath = "//button[text() = \"Checkout\"]")
WebElement CHECKOUTBUTTON;
@FindBy(xpath = "//input[@name=\"firstName\"]")
WebElement FIRSTNAME;
@FindBy(xpath = "//input[@id=\"last-name\"]")
WebElement LASTNAME;
@FindBy (xpath = "//input[@id=\"postal-code\"]")
WebElement ZIPCODE;
@FindBy(xpath = "//input[@id=\"continue\"]")
WebElement CONTINUEBUTTON;
@FindBy(xpath = "//div[@class=\"cart_footer\"]//button[@id=\"finish\"]")
WebElement FINISHBUTTON;
@FindBy(xpath = "//div[@id=\"checkout_complete_container\"]//button[text()=\"Back Home\"]")
WebElement BACKTOHOME;

public void checkout_button()
{
	CHECKOUTBUTTON.click();
}

public void firstnamecheck(String first_name)
{

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(FIRSTNAME));
	
	FIRSTNAME.sendKeys(first_name);
}
	
public void lastnamecheck(String last_name)
{
	LASTNAME.sendKeys(last_name);
}
public void zipcodecheck(String zip_code)
{
	ZIPCODE.sendKeys(zip_code);
}		
	
public void continue_check()
{
	CONTINUEBUTTON.click();
}
public void Finish_Check()
{
	FINISHBUTTON.click();
}	
public void back_home()
{
	BACKTOHOME.click();
}	


	
}
