package com.selenium.greatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.libraries.Base;

public class ViewCartPage extends Base{
	private WebElement viewCartBtn;

	public ViewCartPage(){
		viewCartBtn = myLibrary.waitUntilElementPresence(By.cssSelector(".button.button.btn-cart"));		
	}
	
	public CheckOutPage click_ViewCartButton(){
		viewCartBtn.click();
		return new CheckOutPage();
	}
	
}
