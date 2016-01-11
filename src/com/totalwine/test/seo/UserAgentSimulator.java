package com.totalwine.test.seo;

/*
 This script simulates User Agents (mostly BOTS) and reports the behavior of the site
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.totalwine.test.config.ConfigurationFunctions;

public class UserAgentSimulator {
	
	protected WebDriver driver;
	BufferedWriter writer = null;
	String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    File logFile = new File(timeLog);
    
    @BeforeTest
    public void initialize() throws IOException {
    	writer = new BufferedWriter(new FileWriter(logFile));
    }
    
	@DataProvider(name="BotsParameters")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray("C:\\totalwine\\TWMAutomation\\Resources\\AutomatedFlows.xls","Bots", "botName");
        return(retObjArr);
    }
	
	@Test (dataProvider = "BotsParameters")
	public void UserAgentValidation (String userAgent) throws InterruptedException, IOException {
	    
		File file = new File("C:/totalwine/Library/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		Map<String, String> mobileEmulation = new HashMap<String, String>();
		//mobileEmulation.put("deviceName", "Samsung Galaxy S4");
		mobileEmulation.put("userAgent", userAgent);
		Map<String, Object> chromeOptions = new HashMap<String, Object>();
		chromeOptions.put("mobileEmulation", mobileEmulation);
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		driver = new ChromeDriver(capabilities);
		
		//driver.get(ConfigurationFunctions.locationSet+"71.193.51.0");
		driver.get("http://uat.totalwine.com");
		Thread.sleep(5000);
		writer.write(userAgent);
		System.out.print(userAgent);
		
		//Desktop (No Popup)
		if (driver.findElements(By.cssSelector("section.national-promotion")).size()!=0) {
			System.out.println(" is allowed and wasn't presented any popups");
			writer.write(" ,is allowed and wasn't presented any popups");
		}
		//Mobile (No popup)
		else if (driver.findElements(By.cssSelector("section.hp-way-fndg")).size()!=0) {
			System.out.println(" is allowed and wasn't presented any popups (mobile)");
			writer.write(" ,is allowed and wasn't presented any popups (mobile)");
		}
		//Browser Compatibility Message
		else if (driver.findElements(By.id("btn-continue")).size()!=0) { 
				System.out.println(" ,is allowed and was presented a browser incompatible message");
				writer.write(" ,is allowed and was presented a browser incompatible message");
			}
		//Age Gate
		else if(driver.findElements(By.id("btnYes")).size()!=0) {
				System.out.println(" is allowed and was presented an age gate");
				writer.write(" ,is allowed and was presented an age gate");
			}
		//New Site Intro
		else if(driver.findElements(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).size()!=0) {
			System.out.println(" is allowed and was presented the new site intro");
			writer.write(" ,is allowed and was presented the new site intro");
		}
		//Blocked Bot
		else {
			System.out.println(" ,is BLOCKED");
			writer.write(" ,is BLOCKED");
		}
	}
	
	@AfterMethod
	public void closeSession (ITestResult testResult) throws IOException, InterruptedException { 
		if(testResult.getStatus() == ITestResult.FAILURE) { 
			System.out.print(" ,is BLOCKED");
			writer.write(" ,is BLOCKED");
		}
		driver.close();
		writer.newLine();
	}
	
	@AfterTest
	public void closeWriter() throws IOException {
		System.out.println(logFile.getCanonicalPath());
		writer.close();
	}
}
