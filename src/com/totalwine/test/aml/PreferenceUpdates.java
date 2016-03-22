package com.totalwine.test.aml;

/*
 ****  Preference Updates
 ****  Work flow : 
 *  1. Click on "Account" > "Sign into your account"  (from the header) or "Account info" > "Account login" in footer section 
 *  2. In the Sign in popup > Signin using registered email and password.
 *  3. Member's Account Home page appear
 *  4. Click on the "Preferences" link from the left navigation panel.  
 *  5. change "On"/ "Off" in the "Send me email about" section.
 *  6. Check / Un-check  > wine / Beer /  Spirits section
 *  7. Change store preference
 *  8. click on "Save" button
 *  9. In the Account Home page verify message - "Your email preferences have been updated"
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
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.trials.Browser;
import com.totalwine.test.actions.*;

public class PreferenceUpdates extends Browser {

	@DataProvider(name="amlParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"aml", "PreferenceUpdatesUAT");
        return(retObjArr);
    } 

	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();	
	}  

	@Test (dataProvider = "amlParameters") 
	public void PreferenceUpdatesTest (String Email,String Password,String StoreNumber )
			
			throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Preference Updates Test");
		driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		Thread.sleep(5000);

		//** By Passing Age Gate and Welcome Modal
		Checkout.AgeGateWelcome(driver);
		
	    driver.findElement(By.linkText("Sign In/Register")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.linkText("Sign into your account")).click();
	    Thread.sleep(1000);

	    driver.switchTo().frame(driver.findElement(By.id("iframe-signin-overlay")));
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys(Email);
	    driver.findElement(By.id("j_password")).sendKeys(Password);
	    driver.findElement(By.cssSelector(".btn.btn-red.anLoginSubmit")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("html/body/main/section/section[1]/div/aside/section/ul[1]/li[1]/ul/li[5]/a/span")).click();
	    Thread.sleep(3000);
	    
	    // ** Selecting On/Off button using if/else statement
        if (driver.findElement(By.xpath(".//*[@id='firstPrefLogin']/div[2]/div[1]/div[1]/ul/li[2]/a")).isSelected()) {
            driver.findElement(By.xpath(".//*[@id='promotionsPreference']")).click();
        }   else  {
            driver.findElement(By.xpath(".//*[@id='firstPrefLogin']/div[2]/div[1]/div[1]/ul/li[2]/a")).click();
        }

        if (driver.findElement(By.xpath(".//*[@id='eventsAndNewsPreference']")).isSelected()) {
            driver.findElement(By.xpath(".//*[@id='firstPrefLogin']/div[2]/div[2]/div[1]/ul/li[2]/a']")).click();
        }   else  {
            driver.findElement(By.xpath(".//*[@id='eventsAndNewsPreference']")).click();
        }
        
        // ** Selecting radio button using if/else statement
        WebElement checkBox1 = driver.findElement(By.id("c0020"));
        WebElement checkBox3 = driver.findElement(By.xpath(".//*[@id='c0050']"));

        if(!checkBox1.isSelected()){
            checkBox1.click();
        }

        WebElement scroll = driver.findElement(By.xpath(".//*[@id='c0050']"));  
        scroll.sendKeys(Keys.PAGE_DOWN); //  ** Scrolling down page

        if(!checkBox3.isSelected()){
            checkBox3.click();
        }
	    Thread.sleep(2000);
	    
	    driver.findElement(By.xpath(".//*[@id='prefFormSubmit']/div[3]/div/div[4]/div/div/span")).click();
        driver.findElement(By.cssSelector(".btn.btn-red.anPrefSave")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ahp-heading")).isEmpty(),false,"If Preferrence save confirmation does't display then the test will fail");
	    
	    //** Logout
        driver.findElement(By.cssSelector("div.parent-header-wrapper > div > ul > li:nth-child(3) > a")).click();
	    driver.findElement(By.cssSelector("div.loggedin-wrapper > div.signinup-items > div > ul > li:nth-child(1) > a")).click();
	}
}