package rahulshettyacademy.tests;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandaloneTest {
	ExtentReports extent;
	
	//creating report
	
	@BeforeTest
	public void config() {
		//ExtentReports, extentspart report
		String path= System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Web AUtomation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rahul Shetty");
		
	}

	@Test 
	public void standAloneTestTogether() throws InterruptedException {
		ExtentTest test= extent.createTest("Initial Demo");
		String productname="ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		LandingPage landingpage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod=products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname))
				.findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		//for toast container wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//for animating part wait
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//on cart page
		List<WebElement> cartProduct=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProduct.stream().anyMatch(cart-> cart.getText(). equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		
		//click on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//place order page
		Actions action= new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		JavascriptExecutor javascript= (JavascriptExecutor) driver; 
		javascript.executeScript("window.scrollBy(0,450)");
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmmessage=driver.findElement(By.cssSelector(".hero-primary")).getText();

//		Assert.assertTrue(confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
		driver.close();
		test.fail("Result do no match");
		extent.flush();
	}

}
