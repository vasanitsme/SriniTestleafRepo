package week5.day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LearnMultipleBrowser {

	RemoteWebDriver driver;

	@DataProvider(name = "dynamicLoginData")
	public String[][] sampleLoginData() {
		String[][] data = { 
				
				{ "chrome", "http://leaftaps.com/opentaps/", "DemoSalesManager", "crmsfa" },
				{ "firefox", "http://leaftaps.com/opentaps/", "DemoSalesManager", "crmsfa" },
				{ "edge", "http://leaftaps.com/opentaps/", "DemoSalesManager", "crmsfa" },
				{ "chrome", "http://leaftaps.com/opentaps/", "DemoSalesManager", "crmsfa" } };
		
		return data;
	}

	@Test(dataProvider = "dynamicLoginData")
	public void verifyLoginPage(String browser, String url, String username, String password) throws Exception {

		if (browser.equals("firefox")) {

			driver = new FirefoxDriver();

		} else if (browser.equals("edge")) {

			System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");
			driver = new EdgeDriver();

		} else {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("guest");
			// chrome driver initialization
			driver = new ChromeDriver(options);

		}

		// window options
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("decorativeSubmit")).click();
		
		WebElement logoutElement = driver.findElement(By.xpath("//input[@class='decorativeSubmit']"));
		
		boolean isViisble = logoutElement.isDisplayed();
		System.out.println("logout button visibility : " + isViisble);
		
		
		String landingPageTitle = driver.getTitle();
		System.out.println(landingPageTitle);

		if (landingPageTitle.contains("Leadtaps")) {
			System.out.println("Login is successfull");
		}

		else {
			System.out.println("Login faile - may be invalid credentials");
		}

	}

	@AfterMethod
	public void verifyExitBrowser() {

		driver.quit();
	}
}
