package Pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC002_Addproductsintocart_page {

public WebDriver driver;
public TC002_Addproductsintocart_page AP;
public TC002_Addproductsintocart_page(WebDriver driver)
{
	
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//div[@class=\"inventory_item_description\" and .//div[text()=\"Sauce Labs Backpack\"]]//button[text()=\"Add to cart\"]")
WebElement BAG;
@FindBy(xpath = "//div[@class=\"inventory_item_description\" and .//div[text()=\"Test.allTheThings() T-Shirt (Red)\"]]//button")
WebElement TSHIRT;

public void addproductbag()
{
	
	BAG.click();
}
public void addproductshirt()
{
	WebDriverWait t_shirt = new WebDriverWait(driver, Duration.ofSeconds(10));
			t_shirt.until(ExpectedConditions.visibilityOf(TSHIRT));
	
	TSHIRT.click();
}

}
