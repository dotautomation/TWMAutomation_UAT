package com.totalwine.test.rr;

/* Validate the functionality of the RR by going to the Wine, Beer, and Spirits Guide and click on one of the RR
 * 
 * Spirits Guide to RR Workflow
 * Workflow:
 * 	1. Go to the Wine Guide
 *  2. Scroll down to the RR
 *  3. Click on a RR item
 *  4. Verify that the PDP for the RR loads
 *  5. Repeat the process for Spirits and Beer as well
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

public class FromGuides extends Browser{
	public String IP = "71.193.51.0"; //sacramento CA

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
		@Test
	public void FromGuidesRR () throws InterruptedException {
		logger=report.startTest("Load the PDP of an RR from the Guides");
		driver.get(ConfigurationFunctions.locationSet+IP);		//driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(4000);
	    Actions actions =new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
//wine
		WebElement wine = driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/wine/c/c0020']"));
		WebElement wguide = driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/wine-guide']"));
		actions.moveToElement(wine).perform();
		Thread.sleep(2000);
		actions.click(wguide).perform();
		Thread.sleep(3000);
		
		//Assert that you are on the wine guide page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li.active > a.last")).getText(), "Wine Guide");//check the breadcrumbs to make sure on right page
		
		jse.executeScript("window.scrollBy(0,800)", ""); 
		Thread.sleep(1000);	
		driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/wine-guide/white-grape-varietals/albarino-alvarinho']")).click(); //click on a grape varietal to access RR  
		//Assert that you are on the Albarino Alvarinho page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li.active > a.last")).getText(), "Albarino Alvarinho");//check the breadcrumbs to make sure on right page
		Thread.sleep(1000);	
		jse.executeScript("window.scrollBy(0,800)", "");	//scroll down
		Thread.sleep(1000);
		
		//RR Validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-strat-msg']")).isEmpty(),false); //RR title
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-items']")).isEmpty(),false); //RR Items
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-name']")).isEmpty(),false); //RR name
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-volume']")).isEmpty(),false); //RR volume
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-image']")).isEmpty(),false); //RR image
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-priceContainer']")).isEmpty(),false); //RR prices
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-ratingContainer']")).isEmpty(),false); //RR rating
		
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
		
//Spirits
		WebElement spirits = driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/spirits/c/c0030']"));
		WebElement sguide = driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/liquor-guide']"));
		Thread.sleep(1000);		
		actions.moveToElement(spirits).perform();
		Thread.sleep(2000);
		actions.click(sguide).perform();
		Thread.sleep(3000);
		
		//Assert that you are on the liquor guide page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li.active > a.last")).getText(), "Liquor Guide");
	
		jse.executeScript("window.scrollBy(0,1700)", "");//scroll down
		Thread.sleep(1000);	
		
		//RR Validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-strat-msg']")).isEmpty(),false); //RR title
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-items']")).isEmpty(),false); //RR Items
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-name']")).isEmpty(),false); //RR name
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-volume']")).isEmpty(),false); //RR volume
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-image']")).isEmpty(),false); //RR image
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-priceContainer']")).isEmpty(),false); //RR prices
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-ratingContainer']")).isEmpty(),false); //RR rating
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
		
//Beer
		WebElement beer = driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/beer/c/c0010']"));
		WebElement bguide = driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/beer-guide']"));
		actions.moveToElement(beer).perform();
		Thread.sleep(2000);
		actions.click(bguide).perform();
		Thread.sleep(3000);
		
		//Assert that you are on the beer guide page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li.active > a.last")).getText(), "Beer Guide");//check the breadcrumbs to make sure on right page
		
		jse.executeScript("window.scrollBy(0,500)", "");
		Thread.sleep(1000);	
		driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/beer-guide/ale/belgian-beer']")).click();//click on a type of beer to access RR
		//Assert that you are on theBelgian Beer Page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li.active > a.last")).getText(), "Belgian Beer");//check the breadcrumbs to make sure on right page
		jse.executeScript("window.scrollBy(0,1500)", "");	//scroll down
		Thread.sleep(1000);	
		
		//RR Validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-strat-msg']")).isEmpty(),false); //RR title
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-items']")).isEmpty(),false); //RR Items
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-name']")).isEmpty(),false); //RR name
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-volume']")).isEmpty(),false); //RR volume
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-image']")).isEmpty(),false); //RR image
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-priceContainer']")).isEmpty(),false); //RR prices
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='rr-ratingContainer']")).isEmpty(),false); //RR rating
		
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
