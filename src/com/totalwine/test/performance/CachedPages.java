package com.totalwine.test.performance;

/* Workflow:
 * 1. Navigate to each page that is expected to be served by the CDN (cached)
 * 2. Refresh to ensure a cache hit
 * 3. Validate, from the header, that a cache hit is registered.
 * 4. Example Header Contents for a cache hit:
 * 		X-Cache:HIT
 *		X-Cache-Hits:2
 *		X-POOL:USER
 *		X-Served-By:cache-jfk1026-JFK
 *		X-Timer:S1455304184.916392,VS0,VE0
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class CachedPages extends Browser {
	
	private String IP="71.193.51.0";
	
	@DataProvider(name="CachedPages")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"CachedPages", "url");
        return(retObjArr);
    } 
	
	@BeforeMethod
	  public void setUp() throws Exception {
	    driver.manage().window().maximize();
	  }  
	
	@Test (dataProvider = "CachedPages")
	public void CachePagesTest (String pageURL) throws InterruptedException, IOException {
		logger=report.startTest("Cached Pages Test");
		
		driver.get(ConfigurationFunctions.locationSet+IP);
		Thread.sleep(5000);
		driver.findElement(PageGlobal.AgeGateYes).click();
		Thread.sleep(5000);
	    driver.findElement(PageGlobal.NewSiteIntroClose).click();
	    Thread.sleep(5000);
	    
	    driver.get(ConfigurationFunctions.accessURL+pageURL);
	    Thread.sleep(3000);
	    driver.navigate().refresh();
	    
	    URL obj = new URL(ConfigurationFunctions.accessURL+pageURL);
		URLConnection conn = obj.openConnection();
		//Map<String, List<String>> map = conn.getHeaderFields();
		String xcache = conn.getHeaderField("X-Cache");
		String xcachehits = conn.getHeaderField("X-Cache-Hits");
		String vary = conn.getHeaderField("Vary");
		String xserver = conn.getHeaderField("X-Served-By");
		System.out.println(pageURL);
		System.out.println(xcache);
		System.out.println(xcachehits);
		System.out.println(vary);
		System.out.println(xserver);
		
	}
		
}
