package com.totalwine.test.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.totalwine.test.config.ConfigurationFunctions;

import jxl.*;
import jxl.read.biff.BiffException;

public class SearchNullTerms {
	
	@Test
	public void SearchNullTermsTest () throws InterruptedException, IOException, BiffException {
	
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".csv";
		File logFile=new File(timeLog);
		
		//Instantiate output file
		BufferedWriter writer = new BufferedWriter(new FileWriter(logFile));
		writer.write("Search term,Search Type,All stores count,Did you mean?,Top results");
		writer.newLine();
		
		//File file = new File(ConfigurationFunctions.CHROMEDRIVERPATH);
		//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get(ConfigurationFunctions.accessURL+"/?remoteTestIPAddress=71.193.51.0");
		Thread.sleep(5000);
		driver.findElement(By.id("btnYes")).click();
		Thread.sleep(5000);
	    driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
	    Thread.sleep(5000);
	    
	    
	    //Input file (excel)
	    Workbook inputWorkbook = Workbook.getWorkbook(new File("Search.xls"));
	    Sheet inputSheet = inputWorkbook.getSheet(0);
	    int rowCount = inputSheet.getRows();
	    String SearchTerm,SearchType; //SearchType Options = all,product,event,content
	    for (int i=1;i<rowCount;i++) { //Consider title row
	    	SearchTerm = inputSheet.getCell(0,i).getContents();
	    	SearchType = inputSheet.getCell(1,i).getContents();
	    	System.out.println("Searching for: "+SearchTerm+"|"+SearchType);
	    	writer.write(SearchTerm+",");
	    	writer.write(SearchType+",");
	    	//Select Search Type
	    	driver.findElement(By.cssSelector("span.search-left-cont-three-Lines")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.cssSelector("div.suggestion.flyover > a[data-href=\""+SearchType+"\"]")).click();
	    	Thread.sleep(2000);
			driver.findElement(By.id("header-search-text")).clear();
		    driver.findElement(By.id("header-search-text")).sendKeys(SearchTerm); //Enter Search Term in box
		    driver.findElement(By.cssSelector("a[class=\"search-right-cont-mini-search-logo analyticsSearch\"]")).click(); //Click search button
		    Thread.sleep(3000);
		    if (driver.findElements(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).size()!=0) //Check for All stores tab
		    {
			    String allStoreCount = driver.findElement(By.xpath("//ul[@class=\"plp-product-tabs-wrapper\"]/li[2]/h2/a")).getText(); //Extract text from All stores tab
			    //System.out.println(SearchTerm+":"+allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")"))); 
			    String s = Objects.toString(allStoreCount.substring(allStoreCount.indexOf("(") + 1, allStoreCount.indexOf(")")), null); //Extract count of search results from All store tab
				writer.write(s+",");
				//Did you mean?
				if (driver.findElements(By.cssSelector("div.result-count > a > span")).size()!=0)
					writer.write(driver.findElement(By.cssSelector("div.result-count > a > span")).getText()+",");
				else
					writer.write(""+",");
				if (driver.findElements(By.cssSelector("a#plp-productfull-tabs")).size()!=0)
					driver.findElement(By.cssSelector("a#plp-productfull-tabs")).click();
				else driver.findElement(By.cssSelector("a#search-productfull-tabs")).click();
				Thread.sleep(3000);
				int searchResultsCount = driver.findElements(By.cssSelector("h2.plp-product-title > a.analyticsProductName")).size();//Extract results
				String searchResult = "";
				for (int elementCount=1;elementCount<=searchResultsCount;elementCount++) {
					searchResult+=driver.findElement(By.xpath("//li["+elementCount+"]/div/div/div/h2/a")).getText()
							+"("
							+driver.findElement(By.xpath("//li["+elementCount+"]/div/div/div/div/div[contains(@class,'plp-product-qty')]")).getText()
							+")"
							+",";
							//+"\r";
				}
				//System.out.println(searchResult);
				writer.write(searchResult);
		    }
		  //Check for HTTP500
		    else if (driver.findElement(By.cssSelector("h1")).getText().contains("Oops, we are experiencing")) { 
		    	driver.get(ConfigurationFunctions.accessURL+"/?remoteTestIPAddress=71.193.51.0"); //Reaccess homepage
		    	Thread.sleep(3000);
		    	driver.findElement(By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close")).click();
		    	Thread.sleep(3000);
		    	writer.write("HTTP500"); //Indicate HTTP500 occurance for search term in output file 
		    }
		  //Events search 
		    else if (driver.findElements(By.cssSelector("div.js-event-item")).size()!=0) { 
		    	writer.write(String.valueOf(driver.findElements(By.xpath("//div[contains(@class,'js-event-item')]")).size()));
		    }
		    else
		    	writer.write(driver.getCurrentUrl());
		    	writer.newLine();
    	}
	    driver.close();
		writer.close(); //Close output file
	    inputWorkbook.close(); //Close input excel file
	}
	
}
