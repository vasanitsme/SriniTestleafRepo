package week5.day4;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class LeafTopLoginPage {
	
	DateTimeFormatter dtf;
	String timestamp;
	ChromeDriver driver;
	

	@BeforeMethod
	public void verifyLoginPage() throws Exception {
		
		dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		timestamp = LocalDateTime.now().format(dtf);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		// chrome driver initialization
		driver = new ChromeDriver(options);
		// tmestamp
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

	}

	@AfterMethod
	public void verifyExitBrowser() {

		driver.quit();
	}
}
