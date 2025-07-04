package Pom;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC003_Cart_page_remove_product_Pages {

	
	public WebDriver driver;
	public TC003_Cart_page_remove_product_Pages(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath = "//div[@id=\"shopping_cart_container\" and .//a[@class=\"shopping_cart_link\"]]")
	WebElement CARTICON;
	@FindBy(xpath = "//div[@class=\"cart_item_label\" and .//div[text()=\"Test.allTheThings() T-Shirt (Red)\"]]//button[text()=\"Remove\"]")
	WebElement REMOVE_T_SHIRT;
	
	
	public void carticon()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(CARTICON));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", CARTICON  );
		
		CARTICON.click();
	}
	
	public void removetshirt()
	{
		REMOVE_T_SHIRT.click();
	}
	
	
}
