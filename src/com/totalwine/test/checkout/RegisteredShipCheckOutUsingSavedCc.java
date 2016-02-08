package com.totalwine.test.checkout;

/*
 *  Registered user Ship Checkout using saved Credit card
 *  Work flow : 
 *  1. Access �Change location�
 *  2. Click on Choose a shipping destination
 *  3. Choose a state from the drop down and click on �Make this my shipping state�
 *  4. Access PDP for item
 *  5. Add to cart
 *  6. View Cart
 *  7. Initiate registered user Checkout
 *  8. Tab 1 Checkout ( Delivery Address)
 *  9. Tab 2 Checkout (Billing)
 *  10. Tab 3 Checkout (Review and Submit)
 *  11. Order Confirmation Page
 *  12. Verify welcome message (We're happy you've joined the Total Discovery program, ---) displays

 * Technical Modules:
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
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;

import jxl.read.biff.BiffException;

		public class RegisteredShipCheckOutUsingSavedCc extends Browser {

			@DataProvider(name="CheckoutParameters")
		    public Object[][] createData() {
		    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "RegisteredShipCheckOutUsingSavedCcBF");
		        return(retObjArr);
		    } 

			@BeforeMethod
			  public void setUp() throws Exception {
			    driver.manage().window().maximize();	
				 }  

			@Test (dataProvider = "CheckoutParameters")
			public void RegisteredUserShipCheckOutUsingSavedCc (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String FirstName,
					String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email, 
					String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV,String Password)
							
							throws InterruptedException, BiffException, IOException {

//				logger=report.startTest("Registered Ship Checkout using saved credit card Test");
				driver.get(ConfigurationFunctions.locationSet+Location);
				Thread.sleep(5000);
				driver.findElement(By.id("btnYes")).click();
				Thread.sleep(5000);

			    Assert.assertEquals(StoreName, driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText());
//			    logger.log(LogStatus.PASS, "The site is configured for an Ship order");
			    
			    ConfigurationFunctions.highlightElement(driver,driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")));

		   	 	// **  Selecting a product from PDP
				driver.get(ConfigurationFunctions.accessURL+PDP);
				Thread.sleep(3000);

				// **  Add to Cart
				String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
				System.out.println(productId);
				Thread.sleep(2000);

			    driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click(); //Clicking the ATC button
				Thread.sleep (3000);
				
			    driver.get(ConfigurationFunctions.accessURL+"/cart");
			    Thread.sleep(3000);

			    //  ** Shopping Cart
			    WebElement scroll = driver.findElement(By.id("checkout"));

			    scroll.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page
			    driver.findElement(By.cssSelector("input[id='zipCode']")).click();
			    driver.findElement(By.cssSelector("input[id='zipCode']")).clear();
			    driver.findElement(By.cssSelector("input[id='zipCode']")).sendKeys(Zip);
		  	    driver.findElement(By.cssSelector("input.anZipForm[value='Submit']")).click();
			    Thread.sleep(1000);

			    driver.findElement(By.cssSelector("#deliveryMode > div.customselect > span.itemval")).click();
			    driver.findElement(By.cssSelector("li[data-val="+ShipOption+"]")).click();
			    Thread.sleep(2000);
			    
			    Assert.assertEquals(driver.findElements(By.cssSelector("div[class=\"width-100 totalDotBorder noBorder ship-cost\"]")).isEmpty(),false); //Validate appearance of shipping cost
			    Assert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false);
			    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false);
			    
			    driver.findElement(By.id("checkout")).click();
			    Thread.sleep(3000);
//			    logger.log(LogStatus.PASS, "Shopping cart elements");

			    // **  Next Page (Login/Checkout as a registered user)
			    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false);
			    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false);
			    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
			    Assert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false);
			    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false);
//			    logger.log(LogStatus.PASS, "Selecting registered checkout");

			    // **  Login
			    driver.findElement(By.id("j_username")).clear();
			    driver.findElement(By.id("j_username")).sendKeys(Email);
			    driver.findElement(By.id("j_password")).clear();
			    driver.findElement(By.id("j_password")).sendKeys(Password);
			    driver.findElement(By.id("checkoutSignIn")).click();
			    Thread.sleep(3000);
//			    logger.log(LogStatus.PASS, "Login");

			    // **  Checkout Tab-1
			    Thread.sleep(2000);
			    
			    driver.findElement(By.id("shiporderhere_8864324616215")).click();
//			    logger.log(LogStatus.PASS, "Clicking on Ship Order Here Radio button");

			    WebElement scroll2 = driver.findElement(By.id("btnShipAuth1")); //  ** Scrolling down page
			    scroll2.sendKeys(Keys.PAGE_DOWN);

			    driver.findElement(By.id("btnShipAuth1")).click();
			    Thread.sleep(2000);
//			    logger.log(LogStatus.PASS, "Checkout Tab 1");

			    // **  Checkout Tab-2
			    driver.findElement(By.id("card_8813397442602")).click();
			    
			    WebElement scroll3 = driver.findElement(By.cssSelector(".btn.btn-red.anContinue")); //  ** Scrolling down page
			    scroll3.sendKeys(Keys.PAGE_DOWN);
			    Thread.sleep(1000);
			    
			    driver.findElement(By.cssSelector(".btn.btn-red.anContinue")).click();
			    Thread.sleep(2000);
//			    logger.log(LogStatus.PASS, "Checkout Tab 2");

			    // **  Checkout Tab-3
			    WebElement scroll4 = driver.findElement(By.cssSelector(".btn-red.btn-place-order.anPlaceOrder")); //  ** Scrolling down page
			    scroll4.sendKeys(Keys.PAGE_DOWN);
			    Thread.sleep(1000);
			    
			    driver.findElement(By.id("check_box_age")).click();
			    
			    driver.findElement(By.cssSelector(".btn-red.btn-place-order.anPlaceOrder")).click();
			    Thread.sleep(2000);
//			    logger.log(LogStatus.PASS, "Checkout Tab 3");

			    // Order Confirmation
			    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
			    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
//			    logger.log(LogStatus.PASS, "Registered Ship Checkout Order Confirmation");
	}
}