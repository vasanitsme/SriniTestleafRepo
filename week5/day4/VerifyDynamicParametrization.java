package week5.day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyDynamicParametrization {

	ChromeDriver driver;
	
	@DataProvider(name = "dynamicLoginData")
	public String[][] sampleLoginData() {
	    String[][] data = {
	        {"http://leaftaps.com/opentaps/", "DemoSalesManager", "crmsfa"},
	        {"http://leaftaps.com/opentaps/", "admin", "crmsfa"},
	        {"http://leaftaps.com/opentaps/", "DemoSalesManager", "admin123"},
	        {"http://leaftaps.com/opentaps/","admin", "admin123"}
	    };
	    return data;
	}
	
	
	@Test(dataProvider = "dynamicLoginData")
	public void verifyLoginPage(String url, String username, String password) throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		// chrome driver initialization
		driver = new ChromeDriver(options);
		// window options
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		String landingPageTitle = driver.getTitle();
		System.out.println(landingPageTitle);
		
		if (landingPageTitle.contains("Leadtaps - Testleaf Automation")) {
			System.out.println("Login is successfull");
		}
		
		else {
			System.out.println("Login faile - may be invalid credentials");
		}
	
		
		

	}

	@AfterMethod
	public void verifyExitBrowser() {

		driver.quit();
	}
}
