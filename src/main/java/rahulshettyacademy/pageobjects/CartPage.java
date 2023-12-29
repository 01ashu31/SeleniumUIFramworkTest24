package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProduct;
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	
	public boolean verifyProductDisplay(String productname) {
		
		Boolean match=cartProduct.stream().anyMatch(cart-> cart.getText(). equalsIgnoreCase(productname));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		
		checkout.click();
		return new CheckoutPage(driver);
	}
	
	

}
