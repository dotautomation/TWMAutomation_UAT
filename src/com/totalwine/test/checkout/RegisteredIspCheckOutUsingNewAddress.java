package com.totalwine.test.checkout;

/*
 ****  Registered ISP Checkout using New address
 ****  Work flow : 
 *  1. Access �Change location�
 *  2. Click on Choose a shipping destination
 *  3. Choose a state from the drop down and click on �Make this my shipping state�
 *  4. Access PDP for item
 *  5. Add to cart
 *  6. View Cart
 *  7. Initiate registered user Checkout
 *  8. Tab 1 Checkout ( Pickup Address)
 *  9. Tab 2 Checkout (Click on Edit and add New Billing Address)
 *  10. Tab 3 Checkout (Review and Submit)
 *  11. Order Confirmation Page
 *  12. Verify welcome message (We're happy you've joined the Total Discovery program, ---) displays

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
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebElement;
	import org.testng.Assert;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import com.relevantcodes.extentreports.LogStatus;
	import com.totalwine.test.config.ConfigurationFunctions;
	import com.totalwine.test.trials.Browser;
	import jxl.read.biff.BiffException;

			public class RegisteredIspCheckOutUsingNewAddress extends Browser {

				@DataProvider(name="CheckoutParameters")
			    public Object[][] createData() {
			    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "RegisteredIspCheckOutUsingNewAddressUAT");
			        return(retObjArr);
			    } 

				@BeforeMethod
				  public void setUp() throws Exception {
				    driver.manage().window().maximize();	
					 }  

				@Test (dataProvider = "CheckoutParameters")
				public void RegisteredIspCheckOutUsingNewAddressTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,
						String ISPOption,String FirstName,String LastName,String Company,String Address1,String Address2,String City,String State,
						String Zip,String Email,String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV,String Password,
						String ItemType,String CardNickname,String AddressNickName)
								
								throws InterruptedException, BiffException, IOException {

					Random rand = new Random();
				    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
				    int randomNum_2 = rand.nextInt((1000 - 1) + 1) + 1;
				    logger=report.startTest("Registered ISP Checkout using New address");
					driver.get(ConfigurationFunctions.locationSet+Location);
					Thread.sleep(5000);
					driver.findElement(By.id("btnYes")).click();
					Thread.sleep(5000);

				    Assert.assertEquals(StoreName, driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")).getText());
				    logger.log(LogStatus.PASS, "The site is configured for an ISP order");
				    ConfigurationFunctions.highlightElement(driver,driver.findElement(By.cssSelector("span.store-details-store-name.flyover-src")));

			   	 	// **  Selecting a product from PDP
					driver.get(ConfigurationFunctions.accessURL+PDP);
					Thread.sleep(3000);

					// **  Adding item to Cart
					String productId = driver.findElement(By.cssSelector("div.anProductId")).getText();
					System.out.println(productId);
					Thread.sleep(1000);

				    driver.findElement(By.xpath("(//button[@id='"+productId+"'])[2]")).click();   //** Clicking the ATC button
					Thread.sleep (3000);
					
				    driver.get(ConfigurationFunctions.accessURL+"/cart");
				    Thread.sleep(3000);

				    WebElement scroll3 = driver.findElement(By.id("checkout")); // ** Scrolling page down upto the element
				    scroll3.sendKeys(Keys.PAGE_DOWN);  

				    driver.findElement(By.cssSelector("#deliveryModeInStore > div.customselect > span.itemval")).click();
				    driver.findElement(By.cssSelector("li[data-val="+ISPOption+"]")).click();
				    
				    Assert.assertEquals(driver.findElements(By.cssSelector("input.anVoucherForm")).isEmpty(),false);
				    Assert.assertEquals(driver.findElements(By.name("qty")).isEmpty(),false);
				    
				    driver.findElement(By.id("checkout")).click();
				    Thread.sleep(3000);

				    // **  Next Page (Verification Login/Checkout as a registered user)
				    Assert.assertEquals(driver.findElements(By.id("j_username")).isEmpty(),false);
				    Assert.assertEquals(driver.findElements(By.id("j_password")).isEmpty(),false);
				    Assert.assertEquals(driver.findElements(By.cssSelector("div.checkStyle > label")).isEmpty(),false);
				    Assert.assertEquals(driver.findElements(By.id("forgotPasswordCheckout")).isEmpty(),false);
				    Assert.assertEquals(driver.findElements(By.id("checkoutSignIn")).isEmpty(),false);
//				    logger.log(LogStatus.PASS, "Selecting registered checkout");
				    
				    // **  Login
				    driver.findElement(By.id("j_username")).clear();
				    driver.findElement(By.id("j_username")).sendKeys(Email);
				    driver.findElement(By.id("j_password")).clear();
				    driver.findElement(By.id("j_password")).sendKeys(Password);
				    driver.findElement(By.id("checkoutSignIn")).click();
				    Thread.sleep(3000);

				    // **  Checkout Tab-1
				    WebElement scroll5 = driver.findElement(By.id("btnPickup")); //  ** Scrolling down page
				    scroll5.sendKeys(Keys.PAGE_DOWN);
				    
				    driver.findElement(By.id("btnPickup")).click();
				    Thread.sleep(2000);
				    
				    // **  Checkout Tab-2 [ Checkout using New billing address ]
				    driver.findElement(By.cssSelector("em.icon.icon-edit")).click();
				    Thread.sleep(8000);
				    
				    driver.findElement(By.id("nickName")).click();
				    driver.findElement(By.id("nickName")).clear();
				    driver.findElement(By.id("nickName")).sendKeys(CardNickname);
				    
					 // ** Creating Random Address Nick Name
				    driver.findElement(By.id("addressNickName")).clear();
				    driver.findElement(By.id("addressNickName")).sendKeys("AutoAddressNickName_"+randomNum+"."+randomNum_2+"totalwine");
			    	String AddressNickName1 = driver.findElement(By.id("addressNickName")).getAttribute("value");
			    	System.out.println("Registered Email Address: "+ AddressNickName1);

				    driver.findElement(By.id("fName")).click();
				    driver.findElement(By.id("fName")).clear();
				    driver.findElement(By.id("fName")).sendKeys(FirstName);
				    
				    driver.findElement(By.id("lName")).click();
				    driver.findElement(By.id("lName")).clear();
				    driver.findElement(By.id("lName")).sendKeys(LastName);
				    
				    driver.findElement(By.id("addressLineOne")).click();
				    driver.findElement(By.id("addressLineOne")).clear();
				    driver.findElement(By.id("addressLineOne")).sendKeys(Address1);
				    
				    WebElement scroll6 = driver.findElement(By.id("city")); //  ** Scrolling down page
				    scroll6.sendKeys(Keys.PAGE_DOWN);

				    driver.findElement(By.id("city")).click();
				    driver.findElement(By.id("city")).clear();
				    driver.findElement(By.id("city")).sendKeys(City);
//				    driver.findElement(By.xpath(".//*[@id='editCardForm']/table/tbody/tr[16]/td[2]/div/div/div/span")).click();
//				    driver.findElement(By.cssSelector("li.undefined.anOption[data-val=\""+State+"\"]")).click();		    
//				    driver.findElement(By.cssSelector("li.undefined.anOption[data-val='US-AK']")).click();
				    driver.findElement(By.id("zipCode")).click();
				    driver.findElement(By.id("zipCode")).clear();
				    driver.findElement(By.id("zipCode")).sendKeys(Zip);
				    driver.findElement(By.id("btnCOSave")).click();  	    
				    Thread.sleep(3000);

				    driver.findElement(By.id("card_8813001375786")).click();
				    
				    WebElement scroll7 = driver.findElement(By.cssSelector(".btn.btn-red.anContinue")); //  ** Scrolling down page
				    scroll7.sendKeys(Keys.PAGE_DOWN);
				    Thread.sleep(1000);
				    
				    driver.findElement(By.cssSelector(".btn.btn-red.anContinue")).click();
				    Thread.sleep(2000);

				    // **  Checkout Tab-3
				    WebElement scroll8 = driver.findElement(By.cssSelector(".btn-red.btn-place-order.anPlaceOrder")); //  ** Scrolling down page
				    scroll8.sendKeys(Keys.PAGE_DOWN);
				    Thread.sleep(1000);
				    
				    driver.findElement(By.id("check_box_age")).click();
				    
				    driver.findElement(By.cssSelector(".btn-red.btn-place-order.anPlaceOrder")).click();
				    Thread.sleep(2000);

				    // Order Confirmation
//				    Assert.assertEquals(driver.findElements(By.cssSelector("div.co-conf-thank-text")).isEmpty(),false);
//				    Assert.assertEquals(driver.findElements(By.cssSelector("div")).isEmpty(),false);
				}
			}