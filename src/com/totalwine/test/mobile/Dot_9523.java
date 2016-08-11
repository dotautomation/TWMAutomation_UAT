package com.totalwine.test.mobile;

/* Validate system behavior upon clicking the search icon in the sticky header
 * Mobile
 * 
 * Mobile Event Workflow
 * Workflow:
 * 
 * 1. On the home page scroll down to the footer
 * 2. when scroll down far enough the search icon in the sticky header will appear
 * 2. Click on the search icon
 * 3. Enter something to search to validate the functionality
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.*;
import org.testng.annotations.Test;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class Dot_9523 extends Browser{
	
	private String IP="72.66.119.61";
	
	@Test 
	public void MobileDot_9523 () throws InterruptedException {
		logger=report.startTest("Dot_9523");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		// **  By passing location
				//driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
				//SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//Assert that the everything is in the top nav except the search icon
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='signin-myaccount']")).isEmpty(),false); //profile icon appears			
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='search-right-cont-mini-cart anCartCount']")).isEmpty(),false); //cart icon appears		
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-navigation fluid-icons']")).isEmpty(),false); //hamburger menu appears		
		Assert.assertEquals(driver.findElements(By.xpath("//img[@title='mob-twm-logo.png']")).isEmpty(),false); //twm logo		
		Assert.assertEquals(driver.findElements(By.xpath("//section[@class='mobile-search-container-alternate']")).isEmpty(),false); //search bar
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-header-2 light-bg']")).isEmpty(),false); //find a store, shopping list, call us
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-location-details mobile-header-2']")).isEmpty(),false); //your store details
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='anMobileSearch']")).isEmpty(),true); //search icon does not appear	

		//scroll to the bottom so that sticky header appears
		WebElement mobilefoot = driver.findElement(By.xpath("//ul[@class='mob-footer-follow-us-icons']"));
		actions.moveToElement(mobilefoot).perform();
		Thread.sleep(5000);
		
		//Assert that the specified logos are there and that you do not see the top nav
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='anMobileSearch']")).isEmpty(),false); //search icon appears		
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='signin-myaccount']")).isEmpty(),false); //profile icon appears			
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='search-right-cont-mini-cart anCartCount']")).isEmpty(),false); //cart icon appears		
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-navigation fluid-icons']")).isEmpty(),false); //hamburger menu appears		
		Assert.assertEquals(driver.findElements(By.xpath("//img[@title='mob-twm-logo.png']")).isEmpty(),false); //twm logo		
		Assert.assertEquals(driver.findElements(By.xpath("//section[@class='mobile-search-container-alternate']")).isEmpty(),true); //search bar
		
		//click on the search icon
		driver.findElement(By.xpath("//div[@class='anMobileSearch']")).click();
		Thread.sleep(2000);
		
		//Assert that the everything is in the top nav except the search icon when the user clicks the search icon
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='signin-myaccount']")).isEmpty(),false); //profile icon appears			
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='search-right-cont-mini-cart anCartCount']")).isEmpty(),false); //cart icon appears		
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-navigation fluid-icons']")).isEmpty(),false); //hamburger menu appears		
		Assert.assertEquals(driver.findElements(By.xpath("//img[@title='mob-twm-logo.png']")).isEmpty(),false); //twm logo		
		Assert.assertEquals(driver.findElements(By.xpath("//section[@class='mobile-search-container-alternate']")).isEmpty(),false); //search bar
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-header-2 light-bg']")).isEmpty(),false); //find a store, shopping list, call us
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mobile-location-details mobile-header-2']")).isEmpty(),false); //your store details
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='anMobileSearch']")).isEmpty(),true); //search icon does not appear	
	
	}
}
