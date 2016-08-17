package com.totalwine.test.pdp;

/* Verify that customer is able to view event video on event details page. 
 * - tested for videos on PDP because could not locate an event with a video -
 * Desktop
 * 
 * PDP Event Workflow
 * Workflow:
 * 
 * 1. Go to PDP with video
 * 2. Click on video icon in the screen
 * 3. Let the video play and then press pause
 * 4. Next exit video modal by pressing the x
 * 5. Scroll back up to view product
 * 
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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class Dot_1805 extends Browser {
	
	private String IP="72.66.119.61";
	
	//load the web page
	//driver.get("http://uat.totalwine.com/beer/ale/ipa-india-pale-ale-/american-ipa/sierra-nevada-torpedo-extra-ipa/p/105911127?s=205&igrules=true");
	//driver.get("http://uat.totalwine.com/beer/ale/ipa-india-pale-ale-/american-ipa/sierra-nevada-torpedo-extra-ipa/p/105911127?s=205&igrules=true");
	//driver.get("http://uat.totalwine.com/beer/ale/ipa-india-pale-ale-/american-ipa/dogfish-head-60-minute-ipa/p/94948126?s=205&igrules=true");
	//driver.get("http://uat.totalwine.com/beer/ale/wheat-ale/american-pale-wheat-ale/samuel-adams-summer-ale/p/10990127?s=205&igrules=true");
	//driver.get("http://uat.totalwine.com/accessories-more/accessories/wine-storage-transport/travel-totes/vingardevalise/p/142098920?s=205&igrules=true");
	//driver.get("http://uat.totalwine.com/beer/ale/amber-red-ale/american-amber-red-ale/new-belgium-fat-tire/p/101731903?s=205&igrules=true");
	//http://uat.totalwine.com/wine/red-wine/cabernet-sauvignon/caymus-cabernet/p/34113750?s=205&igrules=true
	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	 } 
	
	@Test 
	public void DesktopDot_1805 () throws InterruptedException {
		logger=report.startTest("Dot-1805 Test");
		driver.get("http://uat.totalwine.com/beer/ale/amber-red-ale/american-amber-red-ale/new-belgium-fat-tire/p/101731127?s=205&igrules=true");
		//driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(1000);
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
//		Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-tabs-ind-left")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("img.anPDPImage.active")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.breadcrumbs")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("span.tabs-right.anPDPTab")).isEmpty(),false);
			    
		Actions actions = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(1000);
		System.out.println("selecting the video/image now...");
		driver.findElement(By.xpath("//img[@data-slide-to='1']")).click();
		Thread.sleep(1000);
		
		//video popup Validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='modal-body']")).isEmpty(),false); //modal			
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='carousel slide']")).isEmpty(),false); //modal			
		Assert.assertEquals(driver.findElements(By.xpath("//iframe[@id='video_player_2']")).isEmpty(),false); //modal			
		Assert.assertEquals(driver.findElements(By.xpath("//a[@class='btn-close pdp-img-zoom-modal-zoom-reset']")).isEmpty(),false); //close button				
		//Assert.assertEquals(driver.findElements(By.xpath("//a[@class='left carousel-control pdp-img-zoom-modal-zoom-reset']")).isEmpty(),false); //left carousel button
		//Assert.assertEquals(driver.findElements(By.xpath("//div[@class='video-wrapper']")).isEmpty(),false); //video wrapper
		

		//hover into the video wrapper
		WebElement vid = driver.findElement(By.xpath("//iframe[@id='video_player_2']"));
		driver.switchTo().frame(vid);
		Thread.sleep(1000);
		//move to the video wrapper
		Thread.sleep(5000);
		WebElement button = driver.findElement(By.xpath("//button[@data-title-pause='Pause'][@title='Pause']"));
		actions.moveToElement(button);
		actions.click().perform();
		Thread.sleep(1000);
		
		//Assert that you have successfully paused the video
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='play-icon']")).isEmpty(),false); //play button				

		//move out of the frame
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='btn-close pdp-img-zoom-modal-zoom-reset']")).click();
		Thread.sleep(1000);
		System.out.println("\n"+"opened and closed the video");
		jse.executeScript("window.scrollBy(0,-500)", "");
		Thread.sleep(1000);
		
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
//		Assert.assertEquals(driver.findElements(By.cssSelector("span.pdp-tabs-ind-left")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("img.anPDPImage")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.breadcrumbs")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.cssSelector("span.tabs-right.anPDPTab")).isEmpty(),false);  
	}
}
