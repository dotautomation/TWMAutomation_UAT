package com.totalwine.test.pages;

import org.openqa.selenium.By;
	
	
	public class PageGlobal {
		
		//Age Gate
		public static final By AgeGateYes = By.id("btnYes");
		public static final By AgeGateNo = By.id("btnNo");
		
		//New Site Intro
		public static final By NewSiteIntroClose = By.cssSelector("#email-signup-overlay-new-site > div.modal-dialog > div.modal-content > div.modal-body > p.close > a.btn-close");
		
		//Top Nav
		public static final By TopNavClassesEvents = By.cssSelector("a.analyticsHeaderLink[href*=\"/events/\"]");
		public static final By TopNavGuidesAdvice = By.cssSelector("a[href*=\"/guides-and-advice\"]");
		public static final By TopNavFindStore = By.cssSelector("a.analyticsHeaderLink[href*=\"/store-finder\"]");
		public static final By TopNavAccount = By.cssSelector("a[data-modal-id=loggedin-not-wrapper]");
		public static final By TopNavAccountLoggedin = By.cssSelector("a.analyticsHeaderLink[data-modal-id=loggedin-wrapper]");
		public static final By TopNavShoppingList = By.cssSelector("a.analyticsHeaderLink[href*=\"/shoppinglist\"]");
		
		//Search Bar
		
		//Top Level Menu
		
		//Footer
		
		//Location Intercept
		public static final By LocationInterceptNo = By.cssSelector("div.ChooseStoreButtons > button.btn.btn-gray");
		
		//Account Management
		//Not logged in
			public static final By SignUp = By.cssSelector(".btn.btn-red.analyticsSignUp");
			public static final By SignInto = By.cssSelector("a.btn.btn-red.acc-link.analyticsSignIn");
		//Logged in
			public static final By LogOut = By.cssSelector("a.analyticsHeaderLink[href*=\"/logout\"]");
			public static final By AccountProfile = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/profile\"]");
			public static final By SavedCards = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/payment-details\"]");
			public static final By AddressBook = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/address-book\"]");
			public static final By Preferences = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/preferences\"]");
			public static final By OnlineOrderHistory = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/orders\"]");
			public static final By InStorePurchaseHistory = By.cssSelector("a.analyticsHeaderLink[href*=\"/my-account/instoreorders\"]");
			public static final By ShoppingList = By.cssSelector("a.analyticsHeaderLink[href*=\"/shoppinglist\"]");
			public static final By TotalDiscoveryFAQs = By.cssSelector("a.analyticsHeaderLink[href*=\"/total-discovery/faq\"]");
}