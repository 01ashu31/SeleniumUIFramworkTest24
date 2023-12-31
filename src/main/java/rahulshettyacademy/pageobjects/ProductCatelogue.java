package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class ProductCatelogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatelogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> product;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By product1By= By.cssSelector(".mb-3");
	By addToCart =By.cssSelector(".card-body button:last-of-type");
	By toastMesage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProdctList() {
		waitForElementToAppear(product1By);
		return product;
		
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod=getProdctList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod= getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMesage);
		waitforElementToDisappear(spinner);
		
	}

}
