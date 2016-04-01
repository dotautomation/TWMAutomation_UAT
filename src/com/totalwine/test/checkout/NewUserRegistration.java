package com.totalwine.test.checkout;

/*
 *  New User Registration using random credentials or credential from DB 
 *  Work flow : 
 *  1. Click on Sign In / Register link on the header
 *  2. Click on Register button
 *  3. Fill up all the required information in  "Create your account" page
 *  5. Click on the Register button
 *  6. Verify welcome message (We're happy you've joined the Total Discovery program) displays

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
import java.util.Random;
import jxl.read.biff.BiffException;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.pages.PageSignInModal;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class NewUserRegistration extends Browser {

	@DataProvider(name="CheckoutParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"Checkout", "Registration");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
		 }  
	
	@Test (dataProvider = "CheckoutParameters")
	public void NewUserRegistrationTest (String Location,String StoreName,String PDP,String Quantity,String ShipOption,String FirstName,
			String LastName,String Company,String Address1,String Address2,String City,String State,String Zip,String Email,String Password,
			String Phone,String CreditCard,String ExpirationMonth,String ExpirationYear,String CVV)
					
					throws InterruptedException, BiffException, IOException {
		
		logger=report.startTest("New User Registration using random credentials or credential from DB");
		
		//** Random E-mail Creation
		Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    int randomNum_2 = rand.nextInt((1000 - 1) + 1) + 1;

		driver.get(ConfigurationFunctions.locationSet+Location);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
	    // ** Checking for survey pop-up
	    Checkout.SurverPopup(driver);

	    driver.findElement(PageGlobal.TopNavAccount).click();
	    Thread.sleep(2000);
	    driver.findElement(PageGlobal.SignUp).click();
	    Thread.sleep(2000);
	
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
	    
	    // ** Checking for survey pop-up
//	    Checkout.SurverPopup(driver);
			    
//	    Assert.assertEquals(driver.findElements(By.cssSelector("section > div.welcomesection > div:nth-child(1)")).isEmpty(),false, "If account doesn't create then test will fail");
	    
	    
//	    driver.findElement(By.cssSelector("section > div.welcomesection > div.startshopping > a")).click();
//	    PageLoad(driver); // Will not trigger the next control until loading the page
//	    sAssert.assertAll();
		}
	}