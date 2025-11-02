package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class EditLead {
	@Test
	public void verifyEditLead() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		
		ChromeDriver driver = new ChromeDriver(options);
		String url = "http://leaftaps.com/opentaps/";
		String username = "DemoSalesManager";
		String password = "crmsfa";
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
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
			driver.findElement(By.linkText("Leads")).click();
			
			driver.findElement(By.linkText("Create Lead")).click();
			
			driver.findElement(By.xpath("//input[@id='createLeadForm_companyName']")).sendKeys("test companyname");
			driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys("test firstname");
			driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']")).sendKeys("test lastname");
			driver.findElement(By.xpath("//input[@id='createLeadForm_firstNameLocal']")).sendKeys("test localfirstname");
			driver.findElement(By.xpath("//input[@id='createLeadForm_departmentName']")).sendKeys("test department");
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("test description");
			driver.findElement(By.xpath("//input[@id='createLeadForm_primaryEmail']")).sendKeys("test123@abc.com");
			driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys("test firstname");
			
			WebElement state = driver.findElement(By.name("generalStateProvinceGeoId"));
			Select statedd = new Select(state);
			statedd.selectByVisibleText("New York");
			String selectedText5 = statedd.getFirstSelectedOption().getText();
			System.out.println("Option:  " + selectedText5 +" selected successfully");
			driver.findElement(By.className("smallSubmit")).click();
			
			driver.findElement(By.linkText("Edit")).click();
			driver.findElement(By.xpath("//textarea[@name='description']")).clear();
			driver.findElement(By.xpath("//textarea[@name='importantNote']")).sendKeys("test important note");
			driver.findElement(By.xpath("//input[@value='Update']")).click();
						
			String actAccoutPageTitle =  driver.getTitle();
			String expAccountPageTitle = "View Lead | opentaps CRM";
			System.out.println("Lead Page title is : " + actAccoutPageTitle);
			if (actAccoutPageTitle.equals(expAccountPageTitle)) {
				System.out.println("Home Assignment <4>: Edit Lead completed sucuessfully");
				
			}
			else {
				System.out.println("some issue in findiing elements");
			}
		}
		
		else {
			System.out.println("Loginf failed - please try with valid credentials");
			
		}
		
		driver.quit();
	}

}
