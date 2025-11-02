package week5.day3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class VerifyEditLead extends LeafTopLoginPage {
	@Test(priority=4, invocationCount= 3, threadPoolSize= 3)
	public void verifyEditLead() throws Exception {
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
				System.out.println("Edit Lead updated with descriptiona and important note ");
				
			}
			else {
				System.out.println("some issue in findiing elements");
			}
		}
		
}
