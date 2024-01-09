package rahulshettyacademy.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationOderPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatelogue productcatalogue;
	ConfirmationOderPage confirmationOrderPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^I Logged in with user name (.+) and password (.+)$")
	public void logged_in_username_and_password(String userName, String password) {

		productcatalogue = landingpage.loginApplication(userName, password);

	}

	@When("^I add the product (.+) from Cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productcatalogue.getProdctList();
		productcatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = productcatalogue.goToCartPage();
		// on cart page
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		// click on checkout button
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		confirmationOrderPage = checkoutpage.submitOrder();

	}
	
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationPage(String string) {
		
		String confirmmessage=confirmationOrderPage.getConfimationMessage();
//		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	 @Then("^ \"([^\"]*)\" message is displayed$")
	    public void incorrectEmailOrPasswordMessageIsDisplayed(String strArg1) {
		 
		 Assert.assertEquals(strArg1, landingpage.getErrorMessage());
		 driver.close();
	     
	    }

}
