package com.totalwine.test.mobile;

/* Validate Contact Us page displays as expected
 * - tested for videos on PDP because could not locate an event with a video -
 * Mobile
 * 
 * Browse Event Workflow
 * Workflow:
 * 
 * 1. Click on the 'Call Us' button
 * 2. On the displayed popup press cancel
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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class Dot_13766 extends Browser {
	
	private String IP="72.66.119.61";
	
	@Test 
	public void MobileDot_13766 () throws InterruptedException {
		logger=report.startTest("Dot-13766 Test");
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(1000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(1000);
		// **  By passing location
		driver.findElement(By.cssSelector("div.ChooseStoreButtons > button#btnNo.btn.btn-gray")).click();
		SiteAccess.ActionAccessMobileAgeGate(driver);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Call us']")).click();
		Thread.sleep(2000);

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		//use native calls to directly acces the popup window
		//'waitForAppScript': '$.delay(5000); true',
		//'autoAcceptAlerts': False
		//press cancel in the popup window
		alert.dismiss(); 
		Thread.sleep(5000);
		
	}
}