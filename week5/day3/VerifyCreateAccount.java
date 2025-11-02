package week5.day3;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class VerifyCreateAccount extends LeafTopLoginPage {
	
	@Test(priority=2, alwaysRun=true)
	public void verifyCreateAccount() throws Exception {
		
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
			}
			else {
				System.out.println("some issue in findiing elements");
			}
		}
		
		

}
