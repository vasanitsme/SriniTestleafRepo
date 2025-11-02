package week5.day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VerifyStaticParametrization {

	ChromeDriver driver;

	@Parameters({ "url", "userName", "password" })
	@Test
	public void verifyLoginPage(String url, String userName, String password) throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		// chrome driver initialization
		driver = new ChromeDriver(options);
		// window options
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys(userName);
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
