package Pom;

import org.openqa.selenium.WebDriver;

import stepdefinition.TC001_Login_step;

public class pageobjectmanager {

	public WebDriver driver;
	public TC001_Login_pages lp;
	public TC002_Addproductsintocart_page AP;
	public TC003_Cart_page_remove_product_Pages CR;
	public TC004_Checkout_page_purchase_order_pages COP;
	
	public pageobjectmanager(WebDriver driver)
	{
		
		this.driver = driver;
	}
	
	
	public TC001_Login_pages loginscreen()
	{
		
		return lp = new TC001_Login_pages(driver);
	}
	
	public TC002_Addproductsintocart_page selectproducts()
	{
		
		return AP = new TC002_Addproductsintocart_page(driver); 
	}
	
	
	public TC003_Cart_page_remove_product_Pages Cartpageremove()
	{
		
		return CR = new TC003_Cart_page_remove_product_Pages(driver);
	}
	
	public TC004_Checkout_page_purchase_order_pages checkoutorderplaced()
	{
		return COP = new TC004_Checkout_page_purchase_order_pages(driver);
	}
	
}
