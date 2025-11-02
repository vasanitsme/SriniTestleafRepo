package week5.day3;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class VerifyMergeLead extends LeafTopLoginPage {
	
	@Test(priority=6)
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

}
