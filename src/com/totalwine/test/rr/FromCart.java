package com.totalwine.test.rr;

/* Validate the functionality of the RR by going to the shopping cart icon and click on one of the RR, the RR should then bring the user to the PDP page
 * 
 * Shopping Cart to RR Workflow
 * Workflow:
 * 	1. Go the shopping cart
 *  2. Click on a RR item
 *  3. Verify that the PDP for the RR loads
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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;

public class FromCart extends Browser{
	
	public String IP = "71.193.51.0"; //sacramento CA

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
		@Test
	public void FromCartRR () throws InterruptedException {
		logger=report.startTest("Load the PDP of an RR from the Cart");
		driver.get(ConfigurationFunctions.locationSet+IP);		//driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(4000);
	    Actions actions =new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		driver.findElement(By.xpath("//div[@id='cart-popup']")).click(); //click on the shopping cart icon
		Thread.sleep(1000);	
		
		//Assert that you are on the shopping cart page
		sAssert.assertEquals(driver.findElements(By.xpath("//section[@class='delivery-section shopping-cart-empty']")).isEmpty(),false); //shopping cart empty message displayed
		sAssert.assertEquals(driver.findElements(By.xpath("//a[@class='btn btn-red analyticsContinueShopping']")).isEmpty(),false); //continue shopping button displayed
		sAssert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li.active > a.last")).getText(), "Cart");//Breadcrumbs says cart
		sAssert.assertEquals(driver.findElement(By.xpath("//div[@class='page-header']")).getText(), "Your shopping cart");//title on page is Your Shopping Cart
		
		//RR Validation
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-strat-msg']")).isEmpty(),false); //RR title
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-items']")).isEmpty(),false); //RR Items
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-name']")).isEmpty(),false); //RR name
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-volume']")).isEmpty(),false); //RR volume
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-image']")).isEmpty(),false); //RR image
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-priceContainer']")).isEmpty(),false); //RR prices
		sAssert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-ratingContainer']")).isEmpty(),false); //RR rating

		driver.findElement(By.xpath("//div[@class='rr-image']")).click();
		Thread.sleep(7000);	
		//PDP Validation
		//Tab 1 - Overview
		sAssert.assertEquals(driver.findElements(By.cssSelector("section.pdp-tab-overview-prod-img > div.pdp-tab-overview-prod-img-bottle-img.pdp-img-zoom-modal-zoom-reset > img.anPDPImage")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("h1.product-name")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("div.pdp-tab-overview-desc-name > div.wine_details > ul.wine-right-details > li > h2 > a.analyticsCountryState")).isEmpty(),false);
			    //Assert.assertEquals(driver.findElements(By.cssSelector("section.css-hook-desc > div.pdp-tab-overview-desc-price > ul > li > div > span.price-style")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("li.sale-price-present > div > span.price-style-mid")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("#pdpTabs > section.item.pdp-tab-overview > section.pdp-tab-overview-type")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("button.anAddToCart")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("button.anAddToListInit")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("div#overview-qty")).isEmpty(),false);
//			    Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-tabs-ind-left")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("img.anPDPImage.active")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("div.breadcrumbs")).isEmpty(),false);
		sAssert.assertEquals(driver.findElements(By.cssSelector("span.tabs-right.anPDPTab")).isEmpty(),false);
	}
}
