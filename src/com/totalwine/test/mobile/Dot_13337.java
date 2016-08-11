package com.totalwine.test.mobile;

/* Send Product to a Friend
 * Mobile
 * 
 * Mobile Event Workflow
 * Workflow:
 * 
 * 1. Click on Wine
 * 2. Click on the first product
 * 3. Scroll down to the email icon
 * 4 Click on the email icon to send an email to a friend
 * 5. Select Send Email - the required fields will then turn red since they were not filled out
 * 6. Enter the required information in 
 * 7. Select Send Email
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

import java.io.IOException;
import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.totalwine.test.actions.Checkout;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;
import jxl.read.biff.BiffException;

public class Dot_13337 extends Browser {

	@DataProvider(name="PDPParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"PDP", "SendFriend");
        return(retObjArr);
    }  
	
	@Test (dataProvider = "PDPParameters")
	public void MobileDot_13337 (String Location,String PDP,String Name,String Email,String FriendName,String FriendEmail,String Message)

			throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Dot_13337");
		driver.get(ConfigurationFunctions.locationSet+Location);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		PageLoad(driver); // Will not trigger the next control until loading the page
		
		// **  By passing location
		//driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		//SiteAccess.ActionAccessMobileAgeGate(driver);
		
	    // **  Selecting a product from PDP
		driver.get(ConfigurationFunctions.accessURL+PDP);
		Thread.sleep(4000);
		PageLoad(driver); // Will not trigger the next control until loading the page
		driver.findElement(By.xpath("//button[@id='btnYes']")).click();
		Thread.sleep(1000);
		
		//PDP Validation
		/*
		Assert.assertEquals(driver.findElements(By.cssSelector("img.carouselImage.anProductImage")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.xpath("//h1[@class='pdp-carousel-text analyticsProductName']")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='pdp-carousel-details']")).isEmpty(),false); //All stores tab				
		Assert.assertEquals(driver.findElements(By.xpath("//span[@class='mobilerating-stars']")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.xpath("//span[@class='pdp-carousel-quantity-price-qty']")).isEmpty(),false);
		Assert.assertEquals(driver.findElements(By.xpath("//span[@class='pdp-carousel-quantity-price-loc']")).isEmpty(),false);
			*/
		Actions actions = new Actions(driver);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement email = driver.findElement(By.xpath("//a[@class='email-logo']"));//scroll down to the email icon
		actions.moveToElement(email).perform();
		email.click();
		Thread.sleep(1000);
		
		//Assert that the modal pops up
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='modal-body']")).isEmpty(),false);//modal body validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='twm-email-modal-buttons']")).isEmpty(),false);//send email button appears
		Assert.assertEquals(driver.findElements(By.xpath("//form[@id='frmEmailProduct']")).isEmpty(),false);//form validation

		//click on the send email button
		WebElement sendemail = driver.findElement(By.xpath("//button[@class='btn-red anSendProd']"));
		actions.moveToElement(sendemail).perform();
		sendemail.click();
		Thread.sleep(2000);
		
		//Assert that the error message appears for form validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='twm-error-msg']")).isEmpty(),false);//Error message validation
		Assert.assertEquals(driver.findElements(By.xpath("//input[@class='error-ele']")).isEmpty(),false);//inputs show that it was an error
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='modal-body']")).isEmpty(),false);//modal body validation
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='twm-email-modal-buttons']")).isEmpty(),false);//send email button appears


		//now go back in the send email form and add the required information
		driver.findElement(By.xpath("//input[@id='yourName']")).sendKeys(Name);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='yourEmail']")).sendKeys(Email);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='recipientName']")).sendKeys(FriendName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='recipientEmail']")).sendKeys(FriendEmail);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys(Message);
		Thread.sleep(2000);
				
		//Assert to make sure that the user has inputed all of the required fields
		Assert.assertFalse(!(driver.findElement(By.xpath("//input[@id='yourName']")).getText().isEmpty()));
		Assert.assertFalse(!(driver.findElement(By.xpath("//input[@id='yourEmail']")).getText().isEmpty()));
		Assert.assertFalse(!(driver.findElement(By.xpath("//input[@id='recipientName']")).getText().isEmpty()));
		Assert.assertFalse(!(driver.findElement(By.xpath("//input[@id='recipientEmail']")).getText().isEmpty()));
		sendemail.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='btnYes']")).click();
				
		//PDP Validation - check that after submitting that the send button is not on  the page
		Assert.assertEquals(driver.findElements(By.xpath("//section[@class='modal fade twm-email-modal in']")).isEmpty(),true); //validate that the modal is not there anymore
		Assert.assertEquals(driver.findElements(By.xpath("//main[@class='wrapper pdp-wrapper an-productDetailsM']")).isEmpty(),false);
		
		Thread.sleep(6000);

				
	}
}
