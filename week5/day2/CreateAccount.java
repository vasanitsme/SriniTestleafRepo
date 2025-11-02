package week5.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class CreateAccount {
	
	@Test
	public void verifyCreateAccount() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		
		ChromeDriver driver = new ChromeDriver(options);
		String url = "http://leaftaps.com/opentaps/";
		String username = "DemoSalesManager";
		String password = "crmsfa";
		
		
		driver.manage().window().maximize();
		driver.get(url);
		
		driver.findElement(By.id("username")).sendKeys(username);;
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		Thread.sleep(3000);
		String landingPageTitle = driver.getTitle();
		System.out.println(landingPageTitle);
		String expTitle = "Leaftaps - TestLeaf Automation Platform";
		if (landingPageTitle.equals(expTitle)) {
			System.out.println("Login is sucesssfull");
			Thread.sleep(3000);
			driver.findElement(By.id("label")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Accounts")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("accountName")).sendKeys("Srini");
			driver.findElement(By.name("description")).sendKeys("Selenium Automation Tester");
			driver.findElement(By.id("officeSiteName")).sendKeys("LeafTaps");
			driver.findElement(By.id("numberEmployees")).sendKeys("10");
			driver.findElement(By.className("smallSubmit")).click();
			String actAccoutPageTitle =  driver.getTitle();
			String expAccountPageTitle = "Create Account | opentaps CRM";
			System.out.println("Account Page title is : " + actAccoutPageTitle);
			if (actAccoutPageTitle.equals(expAccountPageTitle)) {
				System.out.println("Home Assignment <1>: Create Account");
				driver.close();
			}
			else {
				System.out.println("some issue in findiing elements");
			}
		}
		
		else {
			System.out.println("Loginf failed - please try with valid credentials");
			driver.close();
		}
		
		
	}

}
