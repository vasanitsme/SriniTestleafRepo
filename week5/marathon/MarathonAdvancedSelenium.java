package week5.marathon;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class MarathonAdvancedSelenium {

	public static void main(String[] args) throws Exception {

		String url = "https://www.tatacliq.com/";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("guest");
		ChromeDriver driver = new ChromeDriver(options);

		// max window
		driver.manage().window().maximize();

		// implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// geturl
		driver.get(url);

		// sunscription sweeta lert
		boolean alertIsdisplayed = driver.findElement(By.id("moe-dontallow_button")).isDisplayed();

		if (alertIsdisplayed == true) {
			driver.findElement(By.id("moe-dontallow_button")).click();
		}

		// brands
		driver.findElement(By.xpath("//div[@role='button'][text()='Brands']")).click();
		
		//clicking watch accessories
		driver.findElement(
				By.xpath("//div[@aria-label='Watches & Accessories button, Press right arrow or Enter to expand']"))
				.click();
		
		//clicking casio
		driver.findElement(By.xpath("//div[@class=\"DesktopHeader__brandsDetails\"][text()='Casio']")).click();
		
		//sorting by new arrivals
		//driver.findElement(By.className("SelectBoxDesktop__base")).click();
		WebElement sortDropdown = driver.findElement(By.className("SelectBoxDesktop__hideSelect"));
		Select select = new Select(sortDropdown);
		select.selectByVisibleText("New Arrivals");
		
		Thread.sleep(3000);
		
		//List<WebElement> watches = driver.findElements(By.xpath("//div[@class='ProductModule__dummyDiv']"));
		
		//getting casio watch list price
		List<WebElement> casioList = driver.findElements(By.xpath("//div[@class='ProductDescription__priceHolder']/h3"));
		
		String firstWatchPrice = null;

		for (int i = 0; i < casioList.size(); i++) {
			String watchPrice = casioList.get(i).getText();
			if (i == 0) {
				firstWatchPrice = watchPrice.replaceAll("[^0-9]", "");
				System.out.println("First watch prce is : " + firstWatchPrice);
			}
			System.out.println("Watch list is: " + watchPrice);
		}

		//getting parent handle
		String parentHandle = driver.getWindowHandle();
		System.out.println("My parent handle is : " + parentHandle);
		
		driver.findElement(By.xpath("//div[@class='ProductModule__dummyDiv']")).click();
		//driver.findElement(By.id("ProductModule-MP000000000697411")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handles = new ArrayList<String>(windowHandles);

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				System.out.println("Switched to child window!");

				// Extract the price text
				String watchCost = driver.findElement(By.xpath("//h3[contains(text(),'MRP')]")).getText()
						.replaceAll("[^0-9]", ""); // keep only numbers
				System.out.println("Price of my first watch in the watch list is: " + firstWatchPrice);
				System.out.println("price of my selected watch is: " + watchCost);
				// Compare with the first watch price
				if (watchCost.equals(firstWatchPrice)) {
					System.out.println("✅ Price match found: " + watchCost + "||" + firstWatchPrice);

			}
				
				else {
					System.out.println("✅ Price match NOT found: " + watchCost + "||" + firstWatchPrice);;
				}

				// Close child if needed
				driver.close();

			}

			// driver.quit();

		}

	}
}