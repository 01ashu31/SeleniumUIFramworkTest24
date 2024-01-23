package seleniumchromedevtool;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.fetch.Fetch;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		DevTools devtools = driver.getDevTools();
		devtools.createSession();

		devtools.send(Fetch.enable(Optional.empty(), Optional.empty()));

		devtools.addListener(Fetch.requestPaused(), request -> {
			if (request.getRequest().getUrl().contains("shetty")) {
				String mockUrl = request.getRequest().getUrl().replace("=shetty", "=badGuy");
				System.out.println(mockUrl);

				devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockUrl),
						Optional.of(request.getRequest().getMethod()), Optional.empty(),Optional.empty(),
						 Optional.empty()));
			}
			
			else {
				devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()),
						Optional.of(request.getRequest().getMethod()), Optional.empty(),Optional.empty(),
						 Optional.empty()));
			}

		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.cssSelector("p")).getText());

	}

}
