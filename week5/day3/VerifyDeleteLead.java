package week5.day3;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class VerifyDeleteLead extends LeafTopLoginPage {

	@Test(priority=4, dependsOnMethods = {"week5.day3.VerifyEditLead.verifyEditLead"}, timeOut= 1000, alwaysRun= false)
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
}
