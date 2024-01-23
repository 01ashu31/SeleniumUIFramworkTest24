package seleniumchromedevtool;

import java.awt.RenderingHints.Key;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetGeoLocationTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		//52 12
		
		DevTools devtools= driver.getDevTools();
		devtools.createSession();
		
		Map<String, Object> cooridinates= new HashMap<String, Object>();
		cooridinates.put("latitude", 40);
		cooridinates.put("longitude",3);
		cooridinates.put("accuracy",1);
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride", cooridinates);
		
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
		
		String title=driver.findElements(By.cssSelector(".default-ltr-cache-jpuyb8")).get(0).getText();
		System.out.println(title);
		

	}

}
