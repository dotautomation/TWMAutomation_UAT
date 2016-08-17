package com.totalwine.test.rr;

/* Validate the functionality of the RR by going to a PDP and click on one of the RR
 * 
 * Shopping Cart to RR Workflow
 * Workflow:
 * 	1. Go a PDP page
 *  2. Click through the tabs until the RR appears
 *  3. Click on a RR item
 *  4. Verify that the PDP for the RR loads
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */
import com.totalwine.test.trials.Browser;

import jxl.read.biff.BiffException;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.totalwine.test.actions.Checkout;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;

public class FromPDP extends Browser{

	public String IP = "71.193.51.0"; //sacramento CA

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
		@Test
	public void FromPDPRR () throws InterruptedException {
		logger=report.startTest("Load the PDP of an RR from the PDP");
		driver.get(ConfigurationFunctions.locationSet+IP);		//driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.get("http://uat.totalwine.com/wine/red-wine/pinot-noir/meiomi-pinot-noir/p/14738750?s=920&igrules=true");	
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(4000);
		//driver.get("http://uat.totalwine.com/wine/red-wine/pinot-noir/meiomi-pinot-noir/p/14738750?s=920&igrules=true");	
		//driver.get(ConfigurationFunctions.locationSet+IP);
			
		// **  By passing location
		//driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		//SiteAccess.ActionAccessMobileAgeGate(driver);
		
		//PDP Validation
		//Tab 1 - Overview
		Assert.assertEquals(driver.findElements(By.cssSelector("section.pdp-tab-overview-prod-img > div.pdp-tab-overview-prod-img-bottle-img.pdp-img-zoom-modal-zoom-reset > img.anPDPImage")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("h1.product-name")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-tab-overview-desc-name > div.wine_details > ul.wine-right-details > li > h2 > a.analyticsCountryState")).isEmpty(),false);
		//Assert.assertEquals(driver.findElements(By.cssSelector("section.css-hook-desc > div.pdp-tab-overview-desc-price > ul > li > div > span.price-style")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("li.sale-price-present > div > span.price-style-mid")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToCart")).isEmpty(),false); //only appears when the user can add product to cart
		Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToListInit")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#overview-qty")).isEmpty(),false);
		//Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-tabs-ind-left")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("img.anPDPImage.active")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.breadcrumbs")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("span.tabs-right.anPDPTab")).isEmpty(),false);
		
	    Actions actions =new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		driver.findElement(By.xpath("//span[@class='tabs-right anPDPTab']")).click();
		Thread.sleep(2000);
		
		//Assert that you are on the second part of PDP
	    //Tab 2 - Product Details
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.css-details-pd")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.xpath("//button[@class='btn btn-red anAddToListInit']")).isEmpty(),false); //Save
		driver.findElement(By.xpath("//span[@class='tabs-right anPDPTab']")).click();
		Thread.sleep(2000);	
		
		//Assert that you are on the third part of PDP
	    Assert.assertEquals(driver.findElements(By.xpath("//div[@id='BVRRContainer']")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.xpath("//div[@class='BVDITitle BVDI_QTTitle']")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.xpath("//div[@id='BVRRDisplayContentID']")).isEmpty(),false);
	    Assert.assertEquals(driver.findElements(By.xpath("//button[@class='btn btn-red anAddToListInit']")).isEmpty(),false); //Save
		driver.findElement(By.xpath("//span[@class='tabs-right anPDPTab']")).click();
		Thread.sleep(2000);
	    Assert.assertEquals(driver.findElements(By.xpath("//button[@class='btn btn-red anAddToListInit']")).isEmpty(),false); //Save
		
	    //RR Validation
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-strat-msg']")).isEmpty(),false); //RR title
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-name']")).isEmpty(),false); //RR name
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-volume']")).isEmpty(),false); //RR volume
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-image']")).isEmpty(),false); //RR image
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-priceContainer']")).isEmpty(),false); //RR prices
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-ratingContainer']")).isEmpty(),false); //RR rating
	
		driver.findElement(By.xpath("//div[@class='rr-image']")).click();
		Thread.sleep(7000);	
		
		//PDP Validation
		//Tab 1 - Overview
		Assert.assertEquals(driver.findElements(By.cssSelector("section.pdp-tab-overview-prod-img > div.pdp-tab-overview-prod-img-bottle-img.pdp-img-zoom-modal-zoom-reset > img.anPDPImage")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("h1.product-name")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-tab-overview-desc-name > div.wine_details > ul.wine-right-details > li > h2 > a.analyticsCountryState")).isEmpty(),false);
		//Assert.assertEquals(driver.findElements(By.cssSelector("section.css-hook-desc > div.pdp-tab-overview-desc-price > ul > li > div > span.price-style")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("li.sale-price-present > div > span.price-style-mid")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToCart")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("button.anAddToListInit")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div#overview-qty")).isEmpty(),false);
		//Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-tabs-ind-left")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("img.anPDPImage.active")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.breadcrumbs")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("span.tabs-right.anPDPTab")).isEmpty(),false);
	}
}