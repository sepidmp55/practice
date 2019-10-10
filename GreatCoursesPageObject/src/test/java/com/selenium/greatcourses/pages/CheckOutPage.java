package com.selenium.greatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.libraries.Base;

public class CheckOutPage extends Base{

	public CheckOutPage(){
		String cssLocator = "#shopping-cart-table > tbody > tr.first.odd > td.col-format > div.b-core-ui-select > span.b-core-ui-select__value";
		WebElement instanceVideoDropDown = myLibrary.waitUntilElementPresence(By.cssSelector(cssLocator));
		Assert.assertNotNull(instanceVideoDropDown, "Instant Video Drop-Down element is not visible.");
	}
	
	public void click_CheckOutButton(){
		String cssForCheckOutBtn = "#main-content > div > div.col-main.grid12-9.grid-col2-main.in-col2 > div > div.cart_content_container > div.cart_mobile_header_dot_line > div.cart_checkout_header > button > span > span";
		WebElement checkOutBtn = myLibrary.waitUntilElementPresence(By.cssSelector(cssForCheckOutBtn));
		checkOutBtn.click();
	}
}
