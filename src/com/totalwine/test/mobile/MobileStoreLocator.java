package com.totalwine.test.mobile;

/*
 * Mobile Store Locator Workflow
 * Workflow:
 * 	1. Access the site and access the store locator
 * 	2. Search via the State dropdown
 *  3. Validate the shipping options link 
 *  4. Search via the Zip Code
 *  5. Set store session to a different store from the Store search results page.
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 */

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class MobileStoreLocator extends Browser {
private String IP="98.169.134.0";
	
@Test 
public void MobileStoreLocatorTest () throws InterruptedException {
	logger=report.startTest("Mobile Store Locator Test");
	driver.get(ConfigurationFunctions.locationSet+IP);
	Thread.sleep(5000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(5000);
	
	// **  By passing location
	driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
	Thread.sleep(1000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(2000);
	
	//Click "Find a store" from top nav
	driver.findElement(By.xpath("//a[contains(@href,'totalwine.com/store-finder')]")).click();
//	driver.findElement(By.cssSelector("div.parent-header-wrapper > div > ul > li.hide-on-scroll > a")).click();
	Thread.sleep(3000);
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(2000);

	//Store Finder
    Assert.assertEquals(driver.findElements(By.cssSelector("button#findStoresNearMe")).isEmpty(),false);
    
    //Search by state (dropdown)
    SiteAccess.ActionAccessMobileAgeGate(driver);
    Select stateSearch = new Select(driver.findElement(By.cssSelector("select.store-loc-search-by-state")));
    Thread.sleep(2000);
    stateSearch.selectByVisibleText("Virginia");
    Thread.sleep(2000);
    Assert.assertEquals(driver.findElements(By.cssSelector("div.store-loc-content-tap")).isEmpty(),false);
    String storeName = driver.findElement(By.cssSelector("a.analyticsStoreName")).getText();
    System.out.println(storeName);
    Assert.assertEquals(storeName,"Alexandria");
    
    //Shipping Options
    SiteAccess.ActionAccessMobileAgeGate(driver);
    driver.findElement(By.cssSelector("a.link-ship-option")).click();
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(2000);
    Assert.assertEquals(driver.findElements(By.cssSelector("select.shipping-loc-search-by-state")).isEmpty(),false);
    driver.findElement(By.xpath("//a[contains(@href,'totalwine.com/store-finder')]")).click();
	Thread.sleep(3000);
    
    //Search by Zip
    driver.findElement(By.id("storelocator-query")).clear();
    driver.findElement(By.id("storelocator-query")).sendKeys("89002");
    SiteAccess.ActionAccessMobileAgeGate(driver);
    driver.findElement(By.cssSelector("input#btnStoreSearch")).click();
	SiteAccess.ActionAccessMobileAgeGate(driver);
	Thread.sleep(2000);
    Thread.sleep(3000);
    String storeResult = driver.findElement(By.cssSelector("div.store-loc-content-store-heading-store-name")).getText();
    System.out.println(storeResult);
    Assert.assertEquals(storeResult,"Henderson");
    Assert.assertEquals(driver.findElements(By.xpath("//a[contains(@href,'/maps.google.com/')]")).isEmpty(),false); //Get Directions link
    SiteAccess.ActionAccessMobileAgeGate(driver);
    driver.findElement(By.cssSelector("button.btn-red.store-locater-button.cart-change-store.anShopThisStore")).click();
    Thread.sleep(5000);
	 }
}