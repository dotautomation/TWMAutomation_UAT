package com.totalwine.test.checkout;

/*
 * Checkout Workflow
 * Workflow:
 * 	1. Access PDP for item
 * 	2. Add to cart
 * 	3. View Cart
 * 	4. Initial registered checkout
 * 	5. Tab 1 Checkout
 *  6. Tab 2 Checkout (Billing Details)
 *  7. Tab 3 Checkout (Confirm)
 *  8. Order Confirmation Page
 *  
 * Technical Modules:
 * 	1. DataProvider: Checkout test input parameters
 * 	2. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	3. Test (Workflow)
 * 	4. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	5. AfterClass
 * 			Quit webdriver
 */

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.testng.*;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.Keys;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class ShipCheckout extends Browser {
	//public String locationSet = "http://twmuatwebserver:webserveruattwm@uat.totalwine.com/?remoteTestIPAddress=";
	//public static WebDriver driver;
	//ProfilesIni profile = new ProfilesIni();
	//FirefoxProfile testProfile = profile.getProfile("WebDriver");

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "ShipcheckoutBF");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
		//driver = new FirefoxDriver(testProfile);
	    driver.manage().window().maximize();	
		   
	  }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void ShipCheckoutTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String Email,String Password, String Phone,String FirstName,String LastName,String Company,String Address1,String Address2,String City,String State,String Zip) throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Registered Ship Checkout Test");
	    SiteAccess.ActionAccessSite(driver, Location);
	    sAssert.assertEquals(driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText(),StoreName,"The Ship To store isn't displayed correctly");
	    logger.log(LogStatus.PASS, "The site is configured for a Shipping order");
	    ConfigurationFunctions.highlightElement(driver,driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")));
		
		// Add to Cart
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(3000);
		String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
		System.out.println(productId);
		Thread.sleep(2000);
	    //driver.findElement(By.xpath("(//button[@id='"+productId+"'])[3]")).click(); //Clicking the ATC button
		Assert.assertEquals(driver.findElements(By.xpath("(//button[@id='"+productId+"'])[2]")).isEmpty(), false,"The ATC button isn't on the PDP indicating that the test item may be OOS");
	    driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click(); //Clicking the ATC button
		//driver.findElement(By.cssSelector("button.btn.btn-red.mini-cart-popup.anAddToCart")).click();
		Thread.sleep (3000);
	    driver.get(ConfigurationFunctions.accessURL+"/cart");
	    Thread.sleep(7000);
	    
	    // Shopping Cart
	    JavascriptExecutor js = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js.executeScript("arguments[0].click();", driver.findElement(By.id("zipCode")));  
	    driver.findElement(By.id("zipCode")).clear();
	    driver.findElement(By.id("zipCode")).sendKeys(Zip);
	    PageLoad(driver); 
	    driver.findElement(By.cssSelector("input.anZipForm")).click();
	    Thread.sleep(9000);
	    PageLoad(driver); 
	    driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")).click();
	    Thread.sleep(7000);
	    driver.findElement(By.cssSelector("li[data-val="+ShipOption+"]")).click();
	    Thread.sleep(7000);
	    JavascriptExecutor js1 = (JavascriptExecutor)driver;  // Finding out elements that are out of sight
	    js1.executeScript("arguments[0].click();", driver.findElement(By.id("checkout"))); 
	    Thread.sleep(5000);
	    PageLoad(driver); 
	    
//	    driver.findElement(By.id("checkout")).sendKeys(Keys.ARROW_DOWN);
//	    driver.findElement(By.id("zipCode")).click();
//	    driver.findElement(By.id("zipCode")).clear();
//	    driver.findElement(By.id("zipCode")).sendKeys(Zip);
//	    driver.findElement(By.cssSelector("input.anZipForm")).click();
//	    Thread.sleep(3000);
//	    JavascriptExecutor js = (JavascriptExecutor)driver;
//	    //driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")).sendKeys(Keys.ARROW_DOWN);;
//	    js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")));
//	    driver.findElement(By.cssSelector("li[data-val="+ShipOption+"]")).click();
//	    Thread.sleep(3000);
//	    sAssert.assertEquals(driver.findElements(By.cssSelector("div[class=\"width-100 totalDotBorder noBorder ship-cost\"]")).isEmpty(),false,"Shipping cost isn't displayed correctly in cart"); //Validate appearance of shipping cost
//	    sAssert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false,"Promo code field isn't displayed correctly");
//	    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false,"Quantity field isn't displayed correctly");
//	    driver.findElement(By.id("checkout")).click();
//	    Thread.sleep(3000);
	    
	    // Next Page (Login/Checkout as a registered user)
	    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false,"Username field isn't displayed correctly");
	    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false,"Password field isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false,"Label on the login page isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false,"Forgot Password link isn't displayed correctly");
	    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false,"Sign in button isn't displayed correctly");
	    
	    //Login
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys(Email);
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys(Password);
	    driver.findElement(By.id("checkoutSignIn")).click();
	    Thread.sleep(3000);
	    
	    // Checkout Tab 1
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.shipping-tab")).isEmpty(),false,"Shipping Tab 1 labelling isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false,"Edit cart link isn't displayed correctly on Tab 1");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.analyticsPromoCode")).isEmpty(),false,"Promo code field isn't displayed correctly on Tab 1");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("section.giftmessage > div.checkStyle > label")).isEmpty(),false,"Gift messaging isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("section.someoneelsepicking > div.checkStyle > label")).isEmpty(),true,"3rd Party pickup is displayed incorrectly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false,"Tab 1 label isn't displayed correctly");
	    
	    String regd_email = driver.findElement(By.id("shipping-email")).getAttribute("value");
	    String regd_phone = driver.findElement(By.id("shipping-phoneNumber")).getAttribute("value");
	    Assert.assertEquals(regd_email, Email,"Email is incorrectly entered");
	    Assert.assertEquals(regd_phone, Phone,"Phone is incorrectly entered");
	    
	    driver.findElement(By.id("nickName")).clear();
	    driver.findElement(By.id("nickName")).sendKeys("test");
	    driver.findElement(By.id("firstName")).clear();
	    driver.findElement(By.id("firstName")).sendKeys(FirstName);
	    driver.findElement(By.id("lastName")).clear();
	    driver.findElement(By.id("lastName")).sendKeys(LastName);
	    driver.findElement(By.id("companyName")).clear();
	    driver.findElement(By.id("companyName")).sendKeys(Company);
	    driver.findElement(By.id("addressLine1")).clear();
	    driver.findElement(By.id("addressLine1")).sendKeys(Address1);
	    driver.findElement(By.id("city")).clear();
	    driver.findElement(By.id("city")).sendKeys(City);
	    driver.findElement(By.id("btnShipAuth1")).click();
	    PageLoad(driver);
	    
	    // Checkout Tab 2
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.billing-tab")).isEmpty(),false,"Shipping Tab 2 isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.analyticsEditCart")).isEmpty(),false,"Edit cart link isn't displayed correctly on Tab 2");
	    //Use Saved Credit Card
	    driver.findElement(By.xpath("//div[4]/span")).click();
	    driver.findElement(By.xpath("//button[@onclick='javascript:selectCard();']")).click();
	    Thread.sleep(10000);
	    
	    // Checkout Tab 3
	    sAssert.assertEquals(driver.findElements(By.cssSelector("a.review-tab")).isEmpty(),false,"Shipping Tab 3 isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-instore\"]")).isEmpty(),false,"Shipping details section isn't displayed correctly on Tab 3");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw co-rvw-pymnt\"]")).isEmpty(),false,"Payment section isn't displayed correctly on Tab 3");
	    //Assert.assertEquals(driver.findElements(By.cssSelector("li[class=\"co-rvw\"]")).isEmpty(),false);
	    sAssert.assertEquals(driver.findElement(By.cssSelector("li.co-rvw.co-rvw-instore > h3")).getText(),"Shipping method", "Shipping method isn't displayed correctly");
	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.plp-list-img-wdlogo > img")).isEmpty(),false,"WD Logo isn't displayed correctly in Tab 3");
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_1\"]")).isEmpty(),false,"Item unit price isn't displayed correctly in Tab 3");
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[data-attr=\"itemPrice_2\"]")).isEmpty(),false,"Item subtotal price isn't displayed correctly on Tab 3");
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total anTax\"]")).isEmpty(),false,"Taxes isn't displayed correctly on Tab 3");
	    Assert.assertEquals(driver.findElements(By.cssSelector("span[class=\"price-text item-total co-pr-item-total\"]")).isEmpty(),false,"Total Price isn't displayed correctly on Tab 3");
	    driver.findElement(By.id("check_box_age")).click();
	    //driver.findElement(By.xpath("//form[@id='placeOrderForm1']/section/div/button")).click();
	    driver.findElement(By.cssSelector("button.btn-red.btn-place-order.anPlaceOrder")).click();
	    Thread.sleep(10000);
	    
	    // Order Confirmation 
//	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-help-link")).isEmpty(),false,"The help link isn't displayed on the Order confirmation page");
//	    sAssert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false,"The thank-you text isn't displayed on the Order confirmation page");
//	    sAssert.assertAll();
	}
}
