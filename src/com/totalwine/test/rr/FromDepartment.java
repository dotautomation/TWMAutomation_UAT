package com.totalwine.test.rr;

/* Validate the functionality of the RR by going to the department names and click on one of the RR
 * 
 * Shopping Cart to RR Workflow
 * Workflow:
 * 	1. Go the Departments (Wine, Beer, Spirits, Accessories & More)
 *  2. Scroll down
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

public class FromDepartment extends Browser{
	
	public String IP = "71.193.51.0"; //sacramento CA

	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
		@Test
	public void FromDepartmentRR () throws InterruptedException {
		logger=report.startTest("Load the PDP of an RR from the Cart");
		driver.get(ConfigurationFunctions.locationSet+IP);		//driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(4000);
	    Actions actions =new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		//wine
		driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/wine/c/c0020']")).click();
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(3000);	
		
		//Assert that you are on the Wine page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li > a.last")).getText(), "Wine");//Breadcrumbs says Wine

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
		driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/spirits/c/c0030']")).click();
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(3000);	
		
		//Assert that you are on the spirits page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li > a.last")).getText(), "Spirits");//Breadcrumbs says Spirits

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
		driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/beer/c/c0010']")).click();
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(3000);	
		
		//Assert that you are on the beer page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li > a.last")).getText(), "Beer");//Breadcrumbs says Spirits

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
		
		//Accessories
		driver.findElement(By.xpath("//a[@href='http://uat.totalwine.com/accessories-more/c/c0050']")).click();
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		
		//Assert that you are on the Accessories and More page
		Assert.assertEquals(driver.findElement(By.cssSelector("div.breadcrumbs > ul > li > a.last")).getText(), "Accessories & More");//Breadcrumbs says Accessories & More

		
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
		//Assert.assertEquals(driver.findElements(By.cssSelector("div.pdp-tab-overview-desc-name > div.wine_details > ul.wine-right-details > li > h2 > a.analyticsCountryState")).isEmpty(),false); //some accessories do not have those details (http://uat.totalwine.com/accessories-more/mixers-water-soda/tonic-water/schweppes-tonic/p/3875010)
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