package rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationOderPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class SubmitorderTest extends BaseTest{

	@Test
	public void submitOrder() throws IOException, InterruptedException {
		
		String productname="ZARA COAT 3";
		
		//From landing page
		ProductCatelogue productcatalogue=landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		
		//on Product catalogue page
		List<WebElement> products=productcatalogue.getProdctList();
		productcatalogue.addProductToCart(productname);
		CartPage cartPage=productcatalogue.goToCartPage();
		
		
		//on cart page
		Boolean match=cartPage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		
		//click on checkout button
		CheckoutPage checkoutpage= cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationOderPage confirmationOrderPage= checkoutpage.submitOrder();
		
		//place order page
		String confirmmessage=confirmationOrderPage.getConfimationMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		
	}
	
	@Test
	public void OrderHistoryTest() {
		
	}

}
