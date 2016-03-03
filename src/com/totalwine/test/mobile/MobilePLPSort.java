package com.totalwine.test.mobile;

/*
 * PLP Sort Workflow
 * Workflow:
 * 	1. Access the Mobile PLP for Wine
 * 	2. Validate sort results for each selection: 
 * 	  a. Most Popular
 *    b. Our Favorites
 *    c. Expert Ratings
 *    d. Customer Ratings
 *    e. Price (High to Low)
 *    f. Price (Low to High)
 *    g. Name (A-Z)
 *    h. Name (Z-A)
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

import java.io.IOException;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.pages.PageHomepage;
import com.totalwine.test.pages.PageProductList;
import com.totalwine.test.trials.Browser;

public class MobilePLPSort extends Browser {

	//private String IP="71.193.51.0";

	@Test 
	public void MobileFilterTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Mobile PLP Sort Test");
		SiteAccess.ActionAccessMobileSite(driver, "71.193.51.0");
		
		//Access Mobile PLP
		driver.findElement(PageHomepage.MobileWineButton).click();
		Thread.sleep(3000);
		
		//Verify default sort "Most Popular"
		Assert.assertEquals(driver.findElement(By.cssSelector("select#sortOptions > option[selected=selected]")).getText().replaceAll("^\\s+", ""), "Most Popular","Most Popular wasn't the default selected option");
		Assert.assertEquals(driver.findElements(By.cssSelector("a.analyticsProductName[href*=kendall-jackson-chardonnay]")).isEmpty(), false); //Popular item is present
		logger.log(LogStatus.PASS, "Most Popular sort is the default. Kendall Jackson Chardonnay appears in the top few results");
		
		//Verify "Our Favorites" sort
		driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Our Favorites");
	    driver.findElement(By.cssSelector("option[value=\"our-favorites\"]")).click();
	    Thread.sleep(3000);
	    for (int i=1;i<=10;i++) {
	    	Assert.assertEquals(driver.findElements(By.cssSelector("ul.plp-list > li:nth-child("+i+") > div > div > div.plp-list-img-wdlogo")).isEmpty(),false); //Top 10 results are WD
	    }
	    logger.log(LogStatus.PASS, "Top 10 results of an Our Favorites sort displays WD products");

	    //Verify "Expert Ratings" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Expert Ratings");
	    driver.findElement(By.cssSelector("option[value=\"expert-ratings\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div > span.plp-list-img-wineSpec-badge > span")).getText(), "100"); //First result is 100 rated
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div > span.plp-list-img-wineSpec-text")).isEmpty(), false);
	    logger.log(LogStatus.PASS, "Expert Ratings sort displays top rated product on top");
	    
		//Verify "Customer Ratings" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Customer Ratings");
	    driver.findElement(By.cssSelector("option[value=\"customer-ratings\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertEquals(driver.findElements(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div.pdpRatingStars > a > span > span[style=\"width:100.0%\"]")).isEmpty(),false); //First result is 5-star
	    logger.log(LogStatus.PASS, "Customer Ratings sort displays 5-star product on top");
	    
		//Verify "Price (highest first)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Price (highest first)");
	    driver.findElement(By.cssSelector("option[value=\"price-desc\"]")).click();
	    Thread.sleep(3000);
	    int TopPrice = (int) Double.parseDouble(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    int SecondPrice = (int) Double.parseDouble(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(2) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    Assert.assertTrue(TopPrice>SecondPrice);
	    logger.log(LogStatus.PASS, "Descending price sort displays highest priced item on top followed by lower priced items");
	    
		//Verify "Price (lowest first)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Price (lowest first)");
	    driver.findElement(By.cssSelector("option[value=\"price-asc\"]")).click();
	    Thread.sleep(3000);
	    TopPrice = (int) Double.parseDouble(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    SecondPrice = (int) Double.parseDouble(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(2) > div > div.plp-product-price >ul > li > span.price")).getText().replaceAll("[^\\d.]+", ""));
	    Assert.assertTrue(TopPrice<SecondPrice,"Ascending Price sort didn't appear correctly");
	    logger.log(LogStatus.PASS, "Ascending price sort displays lowest priced item on top followed by higher priced items");
	    
		//Verify "Name (A-Z)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Name (A-Z)");
	    driver.findElement(By.cssSelector("option[value=\"name-asc\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertTrue(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > h2 > a.analyticsProductName")).getText().startsWith("1"));
	    logger.log(LogStatus.PASS, "Alpha sort displays items with numerals in their title on top");
	    
		//Verify "Name (Z-A)" sort
	    driver.findElement(PageProductList.MobilePLPSort).click();
	    //SortOption.selectByVisibleText("Name (Z-A)");
	    driver.findElement(By.cssSelector("option[value=\"name-desc\"]")).click();
	    Thread.sleep(3000);
	    Assert.assertTrue(driver.findElement(By.cssSelector("ul.plp-list > li:nth-child(1) > div > h2 > a.analyticsProductName")).getText().startsWith("Z"));
	    logger.log(LogStatus.PASS, "Reverse alpha sort displays items with names beginning with Z on top");
	}
}
