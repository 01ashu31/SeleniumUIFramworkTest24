package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.abstractcomponents.OrderPage;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationOderPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class SubmitorderTest extends BaseTest{
	
	String productname="ZARA COAT 3";

	@Test (dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
	
		//From landing page
		ProductCatelogue productcatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
		
		//on Product catalogue page
		List<WebElement> products=productcatalogue.getProdctList();
		productcatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productcatalogue.goToCartPage();
		
		//on cart page
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		//click on checkout button
		CheckoutPage checkoutpage= cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationOderPage confirmationOrderPage= checkoutpage.submitOrder();
		
		//place order page
		String confirmmessage=confirmationOrderPage.getConfimationMessage();
//		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		
	}
	
	@Test (dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatelogue productcatalogue=landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderpage=   productcatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderPageDisplay(productname));
		
	}
	
	
	//json concept implementation
	
	@DataProvider 
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data= getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data/PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider 
	//is used to drive the data and passed the multiple data section
//	public Object[][] getData() {
//		
//		return new Object [][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}
	
//	HashMap<String, String> map= new HashMap<String, String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map2= new HashMap<String, String>();
//	map2.put("email", "shetty@gmail.com");
//	map2.put("password", "Iamking@000");
//	map2.put("product", "ADIDAS ORIGINAL");

}
