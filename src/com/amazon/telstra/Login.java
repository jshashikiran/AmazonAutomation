package com.amazon.telstra;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;


public class Login extends Utility{



	AndroidDriver driver;

	//constructor to initialize driver
	public Login(AndroidDriver driver)
	{
		this.driver=driver;
	}

	//verify top activity
	public boolean checktopactivity() {
		return isTopActivity(driver, packagename);
	}

	//verify sigin button
	public boolean checksigninbutton() {
		return isdisplayed(driver, "com.amazon.mShop.android.shopping:id/sign_in_button");
	}

	//login function 
	public boolean login_check(String username,String pass) throws InterruptedException {


		isTopActivity(driver, packagename);

		//Click on sign in
		clickbyid(driver, "com.amazon.mShop.android.shopping:id/sign_in_button");

		//Click on username
		clickbyxpath(driver, "//android.widget.EditText[@index=1]");

		//Entering the username


		Thread.sleep(4000);

		setText(driver, username); 
		Thread.sleep(4000);


		clickbyxpath(driver, "//android.widget.EditText[@index=1]");
		Thread.sleep(4000);
		//Enter password
		setText(driver, pass);

		Thread.sleep(8000);


		return true;		


	}


	//logout function
	public  void logout() throws InterruptedException {


		clickbyxpath(driver,"//android.widget.ImageView[@content-desc='Navigation panel, button, double tap to open side panel']");

		swipeUp(driver);

		clickbyid(driver,"//android.widget.TextView[@text='Settings']");

		Thread.sleep(2000);


		clickbyxpath(driver,"//android.widget.LinearLayout[@index=5]");


		clickbyxpath(driver,"//android.widget.Button[@text='SIGN OUT']");

	}

}


