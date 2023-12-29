package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
	WebElement selectCounrty;
	
	By result =By.cssSelector(".ta-results");

	
	public void selectCountry(String countryname) {
		Actions action= new Actions(driver);
		action.sendKeys(country, countryname).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCounrty.click();
		
	}
	
	public ConfirmationOderPage submitOrder() throws InterruptedException {
		JavascriptExecutor javascript= (JavascriptExecutor) driver; 
		javascript.executeScript("window.scrollBy(0,450)");
		Thread.sleep(3000);
		submit.click();
		return new ConfirmationOderPage(driver);
	}
	

}
