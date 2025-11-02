package week5.marathon;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class MarathonTestNG {
	@Parameters({ "url", "userName", "password" })
	@Test
	public void verifyMobilePurchase(String url, String userName, String password) throws Exception {
		// String url = "https://dev186915.service-now.com/";
		// String userName = "admin";
		// String password = "Welcome@2025";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		ChromeDriver driver = new ChromeDriver(options);

		// max window
		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// geturl
		driver.get(url);

		// geting title
		String homepageTitle = driver.getTitle();
		System.out.println("My home page title is: " + homepageTitle);

		// sending username
		driver.findElement(By.id("user_name")).sendKeys(userName);

		// sending password
		driver.findElement(By.id("user_password")).sendKeys(password);

		// clicking login button
		driver.findElement(By.id("sysverb_login")).click();

		// verifying login
		String loginTItle = driver.getTitle();
		System.out.println("After login title is :" + loginTItle);

		if (!homepageTitle.equals(loginTItle)) {
			System.out.println("Login successfull");
		} else {
			System.out.println("Login failed - may be credential issue");
		}

		// accessing shadow element
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(20);
		shadow.findElementByXPath("//div[text()='All']").click();
		// shadow.findElementByXPath("//input[@id='filter']").sendKeys("Service
		// Catalog");
		shadow.findElementByXPath("//a[@id='e660172ac611227b00fa88fb47ae3fad']").click();
		WebElement iframe = shadow.findElement("iframe#gsft_main");
		driver.switchTo().frame(iframe);
		driver.findElement(By.xpath("//h2[contains(text(),'Mobiles')]")).click();

		driver.findElement(By.xpath("//td[@class='sc_item_details']")).click();
		driver.findElement(By.xpath("//label[@class='radio-label'][contains(text(),'Yes')]")).click();
		driver.findElement(By.id("IO:43d5c38a9707011021983d1e6253af8a")).sendKeys("99");
		WebElement dataAllowancedd = driver.findElement(By.id("IO:33494b069747011021983d1e6253af45"));

		Select select = new Select(dataAllowancedd);
		select.selectByValue("Unlimited");

		driver.findElement(By.xpath("//label[@class='radio-label'][contains(text(),'512')]")).click();
		driver.findElement(By.id("oi_order_now_button")).click();

		String purchaseMsg = driver.findElement(By.xpath("//span[text()='Thank you, your request has been submitted']"))
				.getText();
		System.out.println(purchaseMsg);
		String requestID = driver.findElement(By.xpath("//a[@id='requesturl']/b")).getText();

		if (purchaseMsg.contains("Thank you, your request has been submitted")) {
			System.out.println("Order sucuessfully placed with request id: " + requestID);
		} else {
			System.out.println("Sorry! Your order placement is failed - retry later");
		}

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshots/MobilePurchaseOrder.png");
		FileUtils.copyFile(srcFile, destFile);

	}

}
