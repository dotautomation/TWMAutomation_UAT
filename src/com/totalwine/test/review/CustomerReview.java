package com.totalwine.test.review;

/*
 ****  Modify shopping cart during Checkout
 ****  Work flow : 
 *  1. First need to find out some promo code from  - HMC (Hybris Management Console) – http://uat.totalwine.com/hmc/hybris > Marketing ( left navigation) >
 *   		Promotions > Search –Attribute ( Available Online ) > Enabled (Yes) > Available online (Yes) > Click on Search > Click on any product >  
 *   			Click on TWM Attributes tab > See the Voucher Codes value ( Ex: 15chard)
 *  2. Add a product into the cart which has promotional offer [ can find out from /hmc/hybris  ]
 *  3. In the shopping cart page insert promo code. 
 *  4. After clicking on the submit button, confirmation msg (Your promotion has been applied successfully.) should be displayed.
 *  4. Click on Remove button below "Your discounts" section
 *  5. confirmation msg (Your promotion has been removed successfully.) should be displayed.
 *  6. Validate all confirmation message
 **** Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke WebDriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close WebDriver
 * 	5. AfterClass
 * 			Quit WebDriver
 */

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;
import com.totalwine.test.actions.*;

public class CustomerReview extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"CustomerReview", "CustomerRatingReviewUAT");
        return(retObjArr);
    } 

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  

	@Test (dataProvider = "CheckoutParameters")
	public void CustomerRatingsReviewsTest (String IP,String PDP,String Email,String Password,String ReviewTitle,String MyReview,String DescribeItem,
			String NickName,String FirstName,String LastName,String Phone,String Address1,String City,String Zip)
					
		throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Writing Customer Ratings/Reviews");
		
		//** Random E-mail Creation
		Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    int randomNum_2 = rand.nextInt((1000 - 1) + 1) + 1;
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
	    
		
//	    //**Sign in modal with credential which has pre-existing order history, shopping list etc. 
//	    Events.CustomLogin(driver);
//	    PageLoad(driver); // Will not trigger the next control until loading the page
//		
//	    //**Checking for presence of merge cart modal
//	    ShoppingList.MergeCartModal(driver);
	    
	    
	    // **  Selecting a product from PDP
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(7000);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
	    
	    //** Accessing Customer Ratings & Reviews Page
	    Assert.assertEquals(driver.findElements(By.cssSelector("section.css-details-pd")).isEmpty(),false);
	    
	    driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(3000);
	    
	    driver.findElement(By.cssSelector("span.tabs-right.anPDPTab")).click();
	    Thread.sleep(3000);
	    
	    
	    driver.findElement(By.cssSelector("#BVRRRatingSummaryLinkWriteID>a")).click();
	    Thread.sleep(6000);

	    //** Registering new email for writing review as for a specific product one review can write from one email
	    driver.switchTo().frame(driver.findElement(By.id("iframe-signin-overlay")));
	    driver.findElement(By.cssSelector(".custom-arrow.analyticsGetStarted.post-links.post-links-bv")).click();
	    Thread.sleep(6000);
	    
	    // ** Filling up "Create your account" page
	    driver.findElement(By.cssSelector("#firstName")).sendKeys(FirstName);
	    driver.findElement(By.cssSelector("#lastName")).sendKeys(LastName);

	    WebElement scroll1 = driver.findElement(By.cssSelector("#email"));  
	    scroll1.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page   
	    
	    
	    // *** Register Email from DB
//	    driver.findElement(By.cssSelector("#email")).sendKeys(Email);
//	    driver.findElement(By.cssSelector("#checkEmail")).sendKeys(Email);
	    
	    // *** Register Random Email
	    driver.findElement(By.cssSelector("#email")).sendKeys("autoemail_"+randomNum+"."+randomNum_2+"@totalwine.com");
    	String email = driver.findElement(By.cssSelector("#email")).getAttribute("value");
    	System.out.println("Registered Email Address: "+email);
	    
	    driver.findElement(By.cssSelector("#checkEmail")).sendKeys("autoemail_"+randomNum+"."+randomNum_2+"@totalwine.com");
//    	String email = driver.findElement(By.cssSelector("#email")).getAttribute("value");

	    driver.findElement(By.cssSelector("#pwd")).sendKeys(Password);
	    driver.findElement(By.cssSelector("#checkPwd")).sendKeys(Password);
	    driver.findElement(By.cssSelector("#phone")).sendKeys(Phone);

	    WebElement scroll2 = driver.findElement(By.cssSelector("#address1"));  
	    scroll2.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
	    driver.findElement(By.cssSelector("#address1")).sendKeys(Address1);
	    driver.findElement(By.cssSelector("#city")).sendKeys(City);
	    driver.findElement(By.cssSelector("div:nth-child(10) > div > div > span > i")).click();
	    Thread.sleep(1000);
	    WebElement element1 = driver.findElement(By.cssSelector("div:nth-child(10) > div > div > div > div > div.jspPane > ul > li:nth-child(54)"));  
	    new Actions(driver).moveToElement(element1).perform();  
	    element1.click();
	    
	    
//	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
//	    js1.executeScript("arguments[0].click;", driver.findElement(By.cssSelector("div:nth-child(10) > div > div > div > div > div.jspPane > ul > li:nth-child(54)"))); 

	    driver.findElement(By.cssSelector("#zipCode")).sendKeys(Zip);
	    WebElement scroll3 = driver.findElement(By.cssSelector("#btnnuregisteration"));  
	    scroll3.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
	    
	    driver.findElement(By.cssSelector("div.form_field-elements > div:nth-child(2) > div > div > span > i")).click();
	    Thread.sleep(5000);
	    WebElement element2 = driver.findElement(By.cssSelector(".opt-ship-state-data.anOption[data-val='1005']"));  
	    new Actions(driver).moveToElement(element2).perform();  
	    element2.click();
    
//	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
//	    js2.executeScript("arguments[0].click;", driver.findElement(By.cssSelector("div:nth-child(2) > div > div > div > div > div.jspPane > ul > li:nth-child(1)")));
	    
	    driver.findElement(By.cssSelector("#checkbox2")).click();
	    driver.findElement(By.cssSelector("#checkbox3")).click();
	    
	    driver.findElement(By.cssSelector("#btnnuregisteration")).click();
	    PageLoad(driver); // Will not trigger the next control until loading the page
	    Thread.sleep(5000);
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //** Filling Customer Ratings & Reviews form
	    
	    
	    driver.findElement(By.cssSelector("#star_link_rating_5")).click();
	    Thread.sleep(1000);
	    
	    
	    driver.findElement(By.cssSelector("#BVFieldRecommendYesLabelID")).click();
	    Thread.sleep(1000);

	    
	    
	    WebElement scroll4 = driver.findElement(By.cssSelector("#BVFieldTitleID"));  
	    scroll4.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
	    driver.findElement(By.cssSelector("#BVFieldTitleID")).sendKeys(ReviewTitle);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.cssSelector("#BVFieldReviewtextID")).sendKeys(MyReview);
	    Thread.sleep(1000);
	    
	    
	    driver.findElement(By.cssSelector("#BVProTextBox1ID")).sendKeys(DescribeItem);
	    Thread.sleep(1000);
	    
	    driver.findElement(By.cssSelector("#BVProTextBox1ID")).sendKeys(DescribeItem);
	    Thread.sleep(1000);
	    
	    
	    
	    driver.findElement(By.cssSelector("#BVFieldUsernicknameID")).sendKeys(NickName);
	    Thread.sleep(1000);
	    
	   
	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#BVFieldAgreedtotermsandconditionsLabelID")));
	    Thread.sleep(1000);
	    
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of site
	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#BVButtonSubmitID > button")));
	    Thread.sleep(3000);
	    
	    // ** Validate whether promo code successfully removed or not
	    sAssert.assertEquals(driver.findElements(By.cssSelector("#BVMessagePageHeaderID")).isEmpty(),false, "Review and Ratings validated"); 
	    Thread.sleep(3000);

		
		
		
//		// **  Adding item to Cart
//		ShoppingCart.ATC(driver);
//	    driver.get(ConfigurationFunctions.accessURL+"/cart");
//	    Thread.sleep(3000);
//
//	    //  ** Adding Promo Code		    
//	    WebElement scroll = driver.findElement(By.cssSelector("#voucherCode"));
//	    scroll.sendKeys(Keys.PAGE_DOWN);   // ** Scrolling page down upto the element   
//	    driver.findElement(By.cssSelector("#voucherCode")).clear();
//	    driver.findElement(By.cssSelector("#voucherCode")).sendKeys(PromoCode);
//	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
//	    js1.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#voucherForm > div.col-4 > input.anVoucherForm")));
//	    Thread.sleep(2000);
//	    
//	    // ** Validate whether promo code successfully applied or not
////	    Assert.assertEquals("Your promotion has been applied successfully.", driver.findElement(By.cssSelector(".error-msg")).getText());
//
//	    //  ** Removing Promo Code	
//	    JavascriptExecutor js2 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
//	    js2.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".cart-remove>a")));
//	    Thread.sleep(3000);
//	    
//	    // ** Validate whether promo code successfully removed or not
////	    sAssert.assertEquals(driver.findElements(By.cssSelector("p.error-msg")).isEmpty(),false, "Validate whether promo code successfully removed or not");  
//	    Thread.sleep(3000);
////	    sAssert.assertAll();
	 }
}