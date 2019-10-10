package com.selenium.greatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.libraries.Base;

public class ChooseAFormatPage extends Base{
	private WebElement instantVideoRadioBtn;

	public ChooseAFormatPage(){
		instantVideoRadioBtn = myLibrary.waitUntilElementPresence(By.cssSelector("#media-format-radio > div:nth-child(1) > label"));
		Assert.assertNotNull(instantVideoRadioBtn, "Instant Video Radio button element is not visible.");
	}
	
	public ChooseAFormatPage selectInstanceVideoRadioBtn(){
		instantVideoRadioBtn.click();
		myLibrary.handleCheckBoxRadioBtn(true, instantVideoRadioBtn);
		return this;
	}
	
	public ViewCartPage clickAddToCartButton(){
		WebElement addToCartBtn = myLibrary.waitUntilElementClickable(By.cssSelector("#add-to-cart-btn > span > span"));
		addToCartBtn.click();
		return new ViewCartPage();
	}
	
}
