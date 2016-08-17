package com.totalwine.test.mobile;

/* Validate that the ATY Tab only contains items carried in the current store or any of the nearby stores within the BDR or within the IFC
 * Mobile
 * 
 * Browse Event Workflow
 * Workflow:
 * 
 * set the location to Mclean, VA
 * click on the wine button on the home screen
 * select the available to you tab
 * select the in store pick up toggle
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

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class Dot_11753 extends Browser {
	
	private String IP="68.178.213.203"; //sets IP address to scotsdale AZ
	
	@Test 
	public void MobileDot_11753 () throws InterruptedException {
		logger=report.startTest("Dot_11753");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		// **  By passing location
				//driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
				//SiteAccess.ActionAccessMobileAgeGate(driver);
				
		//set the current store to Mclean, VA	
		//Click "Find a store" from top nav
				driver.findElement(By.xpath("//a[contains(@href,'totalwine.com/store-finder')]")).click();
				//SiteAccess.ActionAccessMobileAgeGate(driver);
				Thread.sleep(2000);	
				
				//click yes again
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				driver.findElement(By.xpath("//input[@id='storelocator-query']")).sendKeys("Mclean, VA");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='btnStoreSearch']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				
				//scroll to the button
				WebElement chngStore = driver.findElement(By.xpath("//button[@class='store-locater-button cart-change-store anShopThisStore']"));
				actions.moveToElement(chngStore).perform();
				Thread.sleep(2000);	
				chngStore.click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@title='Wine']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@id='plp-aty-tab']")).click();
				Thread.sleep(4000);
			
			  //PLP Validation
				sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > a#plp-aty-tab")).isEmpty(),false);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > a#plp-productfull-tabs")).isEmpty(),true);

				driver.findElement(By.xpath("//input[@id='header-search-text']")).sendKeys("screaming eagle");
				driver.findElement(By.xpath("//input[@id='header-search-text']")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				Thread.sleep(4000);
				
				//PLP Validation
				sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > a#plp-aty-tab")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > a#search-productfull-tabs")).isEmpty(),false);
	
				//next click on the Find A Store tab to change from ISP to shipping
				driver.findElement(By.xpath("//a[contains(@href,'totalwine.com/store-finder')]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//span[@id='ship-to-state']")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//select[@id='shipping-loc-search-by-state']")).sendKeys("Vi");
				driver.findElement(By.xpath("//select[@id='shipping-loc-search-by-state']")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				
				//change your  shipping state
				driver.findElement(By.xpath("//button[@id='changeShippingState']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				Thread.sleep(4000);
			
				driver.findElement(By.xpath("//input[@id='header-search-text']")).sendKeys("screaming eagle");
				driver.findElement(By.xpath("//input[@id='header-search-text']")).sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				Thread.sleep(4000);
				
				//PLP Validation
				sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > a#plp-aty-tab")).isEmpty(),true);
				sAssert.assertEquals(driver.findElements(By.cssSelector("li.active > a#search-productfull-tabs")).isEmpty(),false);
	}
}
