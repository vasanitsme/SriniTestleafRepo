package week5.day4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class VerifyCreateLead extends LeafTopLoginPage {

	@Test
	public void verifyCreatelead() throws Exception {

		String timestamp = LocalDateTime.now().format(dtf);
		String firstName = "FirstName " + timestamp;
		String lastName = "LastName" + timestamp;
		String companyName = "myCompany" + timestamp;
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
		String actAccoutPageTitle = driver.getTitle();
		String expAccountPageTitle = "View Lead | opentaps CRM";
		System.out.println("Lead Page title is : " + actAccoutPageTitle);
		if (actAccoutPageTitle.equals(expAccountPageTitle)) {

			String leadfirstName = driver.findElement(By.id("viewLead_firstNameLocal_sp")).getText();
			String leadlastName = driver.findElement(By.id("viewLead_lastNameLocal_sp")).getText();

			System.out.println(
					"Lead created sucuessfully with FirstName: " + leadfirstName + " || LastName:" + leadlastName);
		} else {
			System.out.println("Failed to create Lead");
		}
	}

}
