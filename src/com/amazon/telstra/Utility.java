package com.amazon.telstra;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.internal.thread.ThreadExecutionException;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.touch.offset.PointOption;

public class Utility {
	AndroidDriver driver;
	String packagename="com.amazon.mShop";
	public static Properties prop;
	public Utility()	{
		driver=null;
	}

	//Function to get driver object
	public void createDriverInstance() throws MalformedURLException {

		try {
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appDir = new File(classpathRoot, "/Apks/Amazon/");
			File app = new File(appDir, "Amazon_shopping.apk");
			
			File appDir_prop = new File(classpathRoot, "/Input/");
			File app_prop = new File(appDir_prop, "app.properties");
			prop = new Properties();
			FileInputStream ip = new FileInputStream(app_prop);
			prop.load(ip);

			
			Assert.assertEquals(true, app.exists());

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
			capabilities.setCapability("deviceName", "4fdb15d2");
			capabilities.setCapability("platformVersion", "10");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
			capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	//function to retrun driver
	public AndroidDriver getAndroidDriver()
	{
		return driver;
	}

	//function to check if element is displayed
	public boolean isdisplayed(AndroidDriver driver,String id){
		MobileElement element_id = (MobileElement) driver.findElementById(id);

		if(element_id.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//function to close driver
	public void close_driver(AndroidDriver driver){

		driver.quit();
	}

	//Function to set WebView can be extended to App and web
	public void setWebViewContext(AndroidDriver driver){
		Set<String> contextNames = driver.getContextHandles();
		driver.context(contextNames.toArray()[1].toString()); // set context to WEBVIEW_1*/
	}

	//function to set and getwebdriverwait
	public WebDriverWait getWebdriverWait(AndroidDriver driver,int waittime) {

		return new WebDriverWait(driver,waittime);
	}

	//Function to click by id
	public void clickbyid(AndroidDriver driver,String id){
		try {
			WebDriverWait wait = getWebdriverWait(driver, 10);
			MobileElement element_id = (MobileElement) driver.findElementById(id);



			wait.until(ExpectedConditions.visibilityOf(element_id)).click();




		}
		catch(NoSuchElementException e)	{

			e.printStackTrace();
		}	
		catch(Exception e)	{
			e.printStackTrace();
		}
	}
	//Function to click by id
	public void clickbyxpath(AndroidDriver driver,String id){
		try {
			MobileElement element_id = (MobileElement) driver.findElementByXPath(id);

			WebDriverWait wait = getWebdriverWait(driver, 10);


			wait.until(ExpectedConditions.visibilityOf(element_id)).click();




		}
		catch(NoSuchElementException e)	{
			e.printStackTrace();
		}	
		catch(Exception e)	{
			e.printStackTrace();
		}	

	}

	//function to check top activity
	public boolean isTopActivity(AndroidDriver driver,String packagename){




		return driver.getCurrentPackage().contains(packagename);




	}

	//function to set text
	public void setText(AndroidDriver driver,String text){
		try {

			Actions action = new Actions(driver);
			System.out.print(text);
			action.sendKeys(text.toString()).perform();
			action.sendKeys(Keys.ENTER).build().perform();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}

	}

	//Function to Swipe up
	public void swipeUp(AndroidDriver driver){
		try {
			int heightOfScreen = driver.manage().window().getSize().getHeight();
			int widthOfScreen = driver.manage().window().getSize().getWidth();        

			int middleHeightOfScreen = heightOfScreen/2;
			// To get 85% of width

			int x = (int) (widthOfScreen * 0.85);
			// To get 25% of height
			int y = (int) (heightOfScreen * 0.25);


			TouchAction swipe = new TouchAction(driver)
					.press(PointOption.point(0,x))
					.waitAction()
					.moveTo(PointOption.point(0,y))
					.release()
					.perform();


		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
		}

	}


	//function to gettext
	public String getTextById(AndroidDriver ad,String id) {
		MobileElement element_id = (MobileElement) driver.findElementById(id);
		return element_id.getText();

	}

}
