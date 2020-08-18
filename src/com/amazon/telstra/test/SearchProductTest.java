package com.amazon.telstra.test;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.telstra.Login;
import com.amazon.telstra.Search;
import com.amazon.telstra.Utility;

import io.appium.java_client.android.AndroidDriver;


public class SearchProductTest extends Utility {

	Login login;
	Search search;
	AndroidDriver driver;

	public SearchProductTest() {
		super();
	}

	@BeforeTest
	public void setUp() throws MalformedURLException, InterruptedException {
		createDriverInstance();
		driver=getAndroidDriver();
		login = new Login(driver);
		login.login_check(prop.getProperty("username"), prop.getProperty("pass"));
	}

	//check home page
	@Test(priority=1)
	public void validateHomePageLogoTest() throws InterruptedException {
		search.checkhomepage();

	}


	//check item searched and purchase is same
	@Test(priority=2)
	public void verifyItem() throws InterruptedException {
		boolean value=search.search_checkout_validate();
		Assert.assertEquals(value, true);
	}

	@AfterTest
	public void closingBrowser() {
		close_driver(driver);
	}

}
