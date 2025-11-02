package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DuplicateLead {
	@Test
	public void verifyDuplicateLead() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		
		ChromeDriver driver = new ChromeDriver(options);
		String url = "http://leaftaps.com/opentaps/";
		String username = "DemoSalesManager";
		String password = "crmsfa";
		String firstName = "FirstName";
		String lastName = "LastName";
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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
			Thread.sleep(1000);
			driver.findElement(By.id("label")).click();
			Thread.sleep(1000);
			driver.findElement(By.linkText("Leads")).click();
			
			driver.findElement(By.linkText("Create Lead")).click();
			driver.findElement(By.xpath("//input[@class='inputBox' and @name='companyName']")).sendKeys("LeafTop");
			driver.findElement(By.xpath("//input[@class='inputBox' and @name='firstName']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@class='inputBox' and @name='lastName']")).sendKeys(lastName);
			driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("firstname");
			driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys("lastname");
			driver.findElement(By.id("createLeadForm_generalProfTitle")).sendKeys("title");
			driver.findElement(By.className("smallSubmit")).click();
			String actAccoutPageTitle =  driver.getTitle();
			String expAccountPageTitle = "View Lead | opentaps CRM";
			System.out.println("Lead Page title is : " + actAccoutPageTitle);
			if (actAccoutPageTitle.equals(expAccountPageTitle)) {
				
				driver.findElement(By.xpath("//a[@class=\"subMenuButton\" and text()='Duplicate Lead']")).click();
				
				Thread.sleep(1000);
				
				driver.findElement(By.xpath("//input [@class=\"smallSubmit\" and  @value=\"Create Lead\"]")).click();
				
				String leadfirstName = driver.findElement(By.id("viewLead_firstNameLocal_sp")).getText();
				Thread.sleep(1000);
				String leadlastName = driver.findElement(By.id("viewLead_lastNameLocal_sp")).getText();
				
				System.out.println("Duplicate lead created sucuessfully with FirstName: " + leadfirstName +"|| LastName:" + leadlastName );
				driver.quit();
			}
			else {
				System.out.println("Failed to create Duplicate lead");
			}
		}
		
		else {
			System.out.println("Loginf failed - please try with valid credentials");
			driver.quit();
		}
		
		
	}

}
