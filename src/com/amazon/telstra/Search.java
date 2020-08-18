package com.amazon.telstra;

import java.util.List;

import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;


public class Search extends Utility {

	AndroidDriver driver;
	ProductData pd;
	
	public Search(AndroidDriver driver)
	{
		this.driver=driver;
	}

	//verify homepage
	public boolean checkhomepage() {
		return isdisplayed(driver, "com.amazon.mShop.android.shopping:id/rs_search_src_text");

	}

	//Verify product search and item selected
	public Boolean search_checkout_validate() throws InterruptedException {

		clickbyid(driver, "com.amazon.mShop.android.shopping:id/rs_search_src_text");
		Thread.sleep(2000);
		setText(driver, "65 inch TV");
		swipeUp(driver);
		Thread.sleep(3000);
		clickbyxpath(driver, "//android.widget.TextView[@text=\"Samsung 163 cm (65 Inches) 4K Ultra HD Smart LED TV UA65RU7100KXXL (Black) (2019 Model)\"]");

		pd=new ProductData("Samsung 163 cm (65 Inches) 4K Ultra HD Smart LED TV UA65RU7100KXXL (Black) (2019 Model");
		Thread.sleep(3000);




		if(getTextById(driver,"//android.view.View[@text=\\\"Samsung 163 cm (65 Inches) 4K Ultra HD Smart LED TV UA65RU7100KXXL (Black) (2019 Model)\\\"]").contains( pd.getproductname()))
		{
			return true;
		}
		else
		{

			return false;
		}


	}

}
