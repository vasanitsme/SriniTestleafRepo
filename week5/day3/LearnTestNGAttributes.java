//TestNG Attributes 
//What ? Meta properties used to provide info for test methods
//Why? ease the execution by dependencies , prioritizing and grouping
//When ? To control execution flow 
 

//Available attributes 
//*Priority, enabled, invocationCount, threadPoolsize, invocationTimeouts, dependsOnMethod, alwaysRun, timeouts
//By default, on multiple test - testNG annotation will run based on ASCI values of method name

//priority - will accept both +ve and -ve but default is 0
//enable - true or false - by default is true
//invocationCount - running a specific test for multiple times to check stability like count = 5- run 5 times - runs sequential 
//threadPoolSize - number of threads for parallel run - runs parallel 
//invocationCount + threadPoolsize = best combo
//invocationTimecount  - run test cases with in the invocation timeout - preferabbly in POM
//dependsOnMethod - specify methods of depedicy

package week5.day3;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNGAttributes {
	
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
	
	@Test(priority=2, invocationCount= 3, threadPoolSize= 3)
	public void verifyEditLead() throws Exception {
			driver.findElement(By.id("label")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("Leads")).click();
			
			driver.findElement(By.linkText("Create Lead")).click();
			
			driver.findElement(By.xpath("//input[@id='createLeadForm_companyName']")).sendKeys(companyName);
			driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']")).sendKeys(lastName);
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
	
	@Test(priority=2, alwaysRun=true)
	public void verifyDuplicateLead() throws Exception {
		String firstName = "FirstName";
		String lastName = "LastName";
		driver.findElement(By.id("label")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Leads")).click();

		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.xpath("//input[@class='inputBox' and @name='companyName']")).sendKeys(companyName);
		driver.findElement(By.xpath("//input[@class='inputBox' and @name='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@class='inputBox' and @name='lastName']")).sendKeys(lastName);
		driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("firstname");
		driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys("lastname");
		driver.findElement(By.id("createLeadForm_generalProfTitle")).sendKeys("title");
		driver.findElement(By.className("smallSubmit")).click();
		String actAccoutPageTitle = driver.getTitle();
		String expAccountPageTitle = "View Lead | opentaps CRM";
		System.out.println("Lead Page title is : " + actAccoutPageTitle);
		if (actAccoutPageTitle.equals(expAccountPageTitle)) {

			driver.findElement(By.xpath("//a[@class=\"subMenuButton\" and text()='Duplicate Lead']")).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath("//input [@class=\"smallSubmit\" and  @value=\"Create Lead\"]")).click();

			String leadfirstName = driver.findElement(By.id("viewLead_firstNameLocal_sp")).getText();
			Thread.sleep(1000);
			String leadlastName = driver.findElement(By.id("viewLead_lastNameLocal_sp")).getText();

			System.out.println("Duplicate lead created sucuessfully with FirstName: " + leadfirstName + "|| LastName:"
					+ leadlastName);
			driver.quit();
		} else {
			System.out.println("Failed to create Duplicate lead");
		}
	}
	
	@Test(priority=2, enabled=false)
	public void verifyMergeLead()   throws Exception {
		
				driver.findElement(By.id("label")).click();
				
				System.out.println("before merge Window currently opened are : " + driver.getWindowHandle());
				System.out.println("before merge Window title is opened are : " + driver.getTitle());
				
				//clicking contacts tab
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				
				//clicking merge cotacts
				driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
				
				Thread.sleep(1000);
				
				String parentWindow = driver.getWindowHandle(); 
				
				//opening first name contacts
				driver.findElement(By.xpath("(//input[@id='partyIdFrom']/following::img[@src='/images/fieldlookup.gif'])[1]")).click();
				
				Set<String> allFirstNameWindows = driver.getWindowHandles();
				for (String window1 : allFirstNameWindows) {
				    if (!window1.equals(parentWindow)) {
				        driver.switchTo().window(window1); // Switch to child
				        System.out.println("Child Window Title: " + driver.getTitle());
				        Thread.sleep(1000);
				        WebElement firstContact = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
				        String firstContactName = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).getText();
				        System.out.println("Slected first contact to merge is: " + firstContactName);
				        firstContact.click();
				        Thread.sleep(1000);
				        
				    }
				}
				
				Thread.sleep(1000);
				
				//coming back to parent window
				driver.switchTo().window(parentWindow);

				//opening second contacts
				driver.findElement(By.xpath("(//input[@id='partyIdFrom']/following::img[@src='/images/fieldlookup.gif'])[2]")).click();
				
				Thread.sleep(1000);
				
				Set<String> allSecondNameWindows = driver.getWindowHandles();
				for (String window2 : allSecondNameWindows) {
				    if (!window2.equals(parentWindow)) {
				        driver.switchTo().window(window2); // Switch to child
				        System.out.println("Child Window Title: " + driver.getTitle());
				        Thread.sleep(1000);
				        WebElement secondContact = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[3]/a"));
				        String secondContactName = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[3]/a")).getText();
				        System.out.println("Selected second contact to merge is: " + secondContactName);
				        secondContact.click();
				        Thread.sleep(1000);
				        
				    }
				}
				
				Thread.sleep(1000);
				
				//coming back to parent window
				driver.switchTo().window(parentWindow);
				//clicking merge button
				driver.findElement(By.xpath("//a[text()='Merge']")).click();
				
				//accept Alert
				driver.switchTo().alert().accept();
				
				//getting title
				String mergeTitle = driver.getTitle();
				
				if(mergeTitle.contains("View Contact")) {
					System.out.println("Contacts merged successfully");
					
				}
				
				else {
					System.out.println("Merging contacts failed");
				}
				
	}
	
	@Test(priority=6, timeOut=900000)
	public void verifyDeleteLead() throws Exception {
			driver.findElement(By.id("label")).click();
			driver.findElement(By.linkText("Leads")).click();
			driver.findElement(By.linkText("Find Leads")).click();
			driver.findElement(By.xpath("//span[text()='Phone']")).click();
			//driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys("");
			//driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			String selLeadId = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).getText();
			System.out.println("Selected lead id is : " + selLeadId);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[text()='Delete']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[text()='Find Leads']")).click();
			driver.findElement(By.xpath("//input[@name='id']")).sendKeys(selLeadId);
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();

			Boolean delMessage = driver.findElement(By.xpath("//div[text()='No records to display']")).isDisplayed();

			if (delMessage = true) {
				System.out.println("Lead : " + selLeadId + " :deleted successfully" + delMessage);
			}

			else {

				System.out.println("Lead : " + selLeadId + " :Failed to delete ");

			}


		} 
	
	


	@AfterMethod
	public void verifyExitBrowser() {

		driver.quit();
	}

}
