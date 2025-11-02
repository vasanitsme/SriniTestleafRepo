package week5.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class DeleteLead {

	@Test
	public void verifyDeleteLead() throws Exception {
		// chrome option to login as guest
		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		// chrome driver initialization
		ChromeDriver driver = new ChromeDriver(options);

		String url = "http://leaftaps.com/opentaps/";
		String username = "DemoSalesManager";
		String password = "crmsfa";

		// window options
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		String landingPageTitle = driver.getTitle();
		System.out.println(landingPageTitle);
		String expTitle = "Leaftaps - TestLeaf Automation Platform";
		if (landingPageTitle.equals(expTitle)) {
			System.out.println("Login is sucesssfull");
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

			driver.quit();

		} else {
			System.out.println("Loginf failed - please try with valid credentials");
			driver.quit();
		}
	}
}
