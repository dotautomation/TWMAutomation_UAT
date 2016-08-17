package com.totalwine.test.storelocator;

/* Validate that the TWM stores can be located by City and/or State combination
 * Desktop
 * 
 * Storelocator Event Workflow
 * Workflow:
 * 
 * 1. Go to the store locator link in the nav
 * 2. In the search bar search for a state where TWM has a store
 * 3. Next search for a city that is mispelled and a valid state
 * 4. Next search for an invalid state with a valid city
 * 5. Next search for a valid state and a valid city (but that city isn't in that state)
 * 
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class Dot_9345 extends Browser {
	private String IP="72.66.119.61";
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test 
	public void DesktopDot_9345 () throws InterruptedException {
		logger=report.startTest("Dot-9345 Test");
		driver.get(ConfigurationFunctions.locationSet+IP);		//driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(8000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.findElement(By.xpath("//a[contains(@href,'totalwine.com/store-finder')]")).click();
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys("Florida");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys(Keys.ENTER);
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(8000);
		
		//Assert that the details on page update to the search
		sAssert.assertEquals(driver.findElements(By.xpath("//a[contains(@href, 'http://uat.totalwine.com/store-info/florida-orlando-millenia-plaza/920')]")).isEmpty(),false); 
	
		jse.executeScript("window.scrollBy(0,-250)", "");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys("Dalls Texas");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys(Keys.ENTER);
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(6000);
		
		//Assert that the details on page update to the search
		sAssert.assertEquals(driver.findElements(By.xpath("//a[contains(@href, 'http://uat.totalwine.com/store-info/texas-dallas-park-lane/501')]")).isEmpty(),false); 

		jse.executeScript("window.scrollBy(0,-250)", "");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys("Txs Dallas");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys(Keys.ENTER);
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(6000);
		
		//Assert that the details on page update to the search
		Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@href, 'http://uat.totalwine.com/store-info/texas-dallas-park-lane/501')]")).isEmpty(),false); 

		jse.executeScript("window.scrollBy(0,-250)", "");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys("Texas Sacramento");
		driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys(Keys.ENTER);
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(6000);
		
		//Assert that the details on page update to the search
		sAssert.assertEquals(driver.findElements(By.xpath("//a[contains(@href, 'http://uat.totalwine.com/store-info/texas-san-antonio-del-norte/503')]")).isEmpty(),false); 

	}
}
