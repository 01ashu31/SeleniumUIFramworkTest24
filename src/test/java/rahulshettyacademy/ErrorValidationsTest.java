package rahulshettyacademy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.abstractcomponents.AbstractComponent;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatelogue;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void LoginErrorValidation() {
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("anshika@gmail.com", "Iamkin@000");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() {
		String productname = "ZARA COAT 3";
		ProductCatelogue productcatalogue = landingpage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement> products = productcatalogue.getProdctList();
		productcatalogue.addProductToCart(productname);
		CartPage cartPage = productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
