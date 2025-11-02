//TestNG parametrization - 1. static and dynamic

//1. Static - pass through testNG xml file 

//@Parameters annotations in test method | for ex. //@parameters({"url", "userName", Password})

//testNG xml - <parameter name = "username">  </>

//Steps for Static :  

//2. Dynamic - pass through testNG xml file
//Pass external data from diffrent source
//@DataProvider - syntax

/*@DataProvider(name = "dynamicLoginData")
    public Object[][] getDynamicLoginData() {
        // In a real scenario, this data could be loaded from an external source
        // like a database, CSV, or API.
        return new Object[][] {
            {"user1", "pass123"},
            {"user2", "pass456"},
            {"user3", "pass789"},
            {"user3", "pass789"}
        };*/


package week5.day4;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LearnTestNGParametrization {
	ChromeDriver driver;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    String timestamp = LocalDateTime.now().format(dtf);
	String url = "http://leaftaps.com/opentaps/";
	String username = "DemoSalesManager";
	String password = "crmsfa";
	String firstName = "FirstName " + timestamp;
	String lastName = "LastName" + timestamp;
	String companyName = "myCompany" + timestamp;
	String accountName = "myAccount" + timestamp;
	
	// 
	// @Test(invocationCount = 3, invocationTimeOut = 10000)
	//@Test (enabled = false, priority = 4)
	//@Test(dependsOnMethods = {"week5.day3.CreateLead.createLead"},alwaysRun = true )
	//@Test(invocationCount = 3, timeOut = 10000)
	
	@BeforeMethod
	public void verifyLoginPage() throws Exception {

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
		System.out.println("Login successfull with title isL " +landingPageTitle);

	}
	
	@Test(priority=1, invocationCount= 1, threadPoolSize= 3)
	public void verifyCreateAccount() throws Exception {
		
			driver.findElement(By.id("label")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Accounts")).click();
			driver.findElement(By.linkText("Create Account")).click();
			driver.findElement(By.id("accountName")).sendKeys(accountName);
			driver.findElement(By.name("description")).sendKeys("Selenium Automation Tester");
			driver.findElement(By.id("officeSiteName")).sendKeys("LeafTaps");
			driver.findElement(By.id("numberEmployees")).sendKeys("10");
			driver.findElement(By.className("smallSubmit")).click();
			Thread.sleep(1000);
			String actAccoutPageTitle =  driver.getTitle();
			String expAccountPageTitle = "Account Details | opentaps CRM";
			System.out.println("Account Page title is : " + actAccoutPageTitle);
			if (actAccoutPageTitle.contains(expAccountPageTitle)) {
				System.out.println("Account created Suceessfully with AccountName:" + accountName);
			}
			else {
				System.out.println("some issue in findiing elements");
			}
		}
	
	@Test(priority=2, invocationCount= 3, threadPoolSize= 3)
	public void verifyCreatelead() throws Exception {
		
				
			driver.findElement(By.id("label")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Leads")).click();
			
			driver.findElement(By.linkText("Create Lead")).click();
			driver.findElement(By.xpath("//input[@class='inputBox' and @name='companyName']")).sendKeys(companyName);
			driver.findElement(By.xpath("//input[@class='inputBox' and @name='firstName']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@class='inputBox' and @name='lastName']")).sendKeys(lastName);
			driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("firstname");
			driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys("lastname");
			driver.findElement(By.id("createLeadForm_generalProfTitle")).sendKeys("title");
			driver.findElement(By.className("smallSubmit")).click();
			Thread.sleep(1000);
			String actAccoutPageTitle =  driver.getTitle();
			String expAccountPageTitle = "View Lead | opentaps CRM";
			System.out.println("Lead Page title is : " + actAccoutPageTitle);
			if (actAccoutPageTitle.equals(expAccountPageTitle)) {
				
				String leadfirstName = driver.findElement(By.id("viewLead_firstNameLocal_sp")).getText();
				String leadlastName = driver.findElement(By.id("viewLead_lastNameLocal_sp")).getText();
				
				System.out.println("Lead created sucuessfully with FirstName: " + leadfirstName +" || LastName:" + leadlastName );
			}
			else {
				System.out.println("Failed to create Lead");
			}
		}
	
}
