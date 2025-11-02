package week5.day3;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class VerifyDuplicateLead extends LeafTopLoginPage {
	@Test(priority=4, dependsOnMethods = {"week5.day3.VerifyDeleteLead.verifyDeleteLead"}, enabled= false)
	public void verifyDuplicateLead() throws Exception {
		String firstName = "FirstName";
		String lastName = "LastName";
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

}
