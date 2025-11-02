// Test NG - Test Next Generation - unit testing Framework 
// latest Version 7.11 (unstable), stable version 7.10
// Why? Flexible test configuration, support data driven testing, support for test reporting and logging
// What need? - testNG plug-in & its Dependencies 
// Where ? Eclipse iDE --> Help -> Eclipse market place -> Test NG  for Eclipse
// Where ? -  Dependencies - MVN repository - org.TestNG
// Feature? - Support multiple execution single run, Support sequential and parallel, Generate report and status
// Supports execution configuration through annotation and attributes, Support multiple test data via CSV or Excel
// Supports cross browser testing, Assertion, Rerun failed test cases

//converting normal test to testNG steps

//Step1: convert main method to normal method - no string args & no static keyword
//step2: Add @Test on top a method and import testNG annotations
//step3: Run testNG as individual test cases

//Parallel test - Parllel is true recommended for not multiple test cases


package week5.day2;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LearnTestNG {
	public ChromeDriver driver;

	@Test
	public void loginPage() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

}
