package com.amazon.telstra.test;
import java.net.MalformedURLException;
import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;


import com.amazon.telstra.Login;
import com.amazon.telstra.Utility;

import io.appium.java_client.android.AndroidDriver;

public class LoginTest extends Utility {

	Login login;


	AndroidDriver driver;


	@BeforeTest
	public void beforetest() throws MalformedURLException {

		//Get driver instance  
		createDriverInstance();
		driver=getAndroidDriver();
		//call login constructor
		login = new Login(driver);
		
	}
	//check sign in button
	@Test(priority=1)
	public void signinbutton() {
		boolean value=login.checksigninbutton();
		Assert.assertTrue(value);

	}

	//Verify login is success
	@Test(priority=2)
	public void checklogin() throws InterruptedException {
		
		
		login.login_check(prop.getProperty("username"), prop.getProperty("pass"));
		login.logout();
	}




	@AfterTest
	public void closingBrowser() {
		close_driver(driver);
	}

}
