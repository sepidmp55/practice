package com.selenium.greatcourses.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.libraries.Base;

public class SciencePage extends Base{

	public SciencePage(){
		// ExplicitWait for the "category-products" region to be present
		WebElement courseRegion = myLibrary.waitUntilElementPresence(By.className("category-products-container"));
		Assert.assertNotNull(courseRegion, "Category Products region is not visible.");
	}

	public ChooseAFormatPage selectOurNightSkyCourse() {
		selectAProductName("Our Night Sky");
		return new ChooseAFormatPage();
	}
	
	public ChooseAFormatPage selectTheAgingBrainCourse() {
		selectAProductName("The Aging Brain");
		return new ChooseAFormatPage();
	}
	
	// Helper method and visible only within this class
	private void selectAProductName(String productName){
		WebElement courseRegion = myLibrary.waitUntilElementPresence(By.className("category-products-container"));
		Assert.assertNotNull(courseRegion);

		List<WebElement> liElems = courseRegion.findElements(By.tagName("li"));
		for (WebElement li : liElems) {
			WebElement productNameElem = li.findElement(By.tagName("h2"));

			System.out.println("Product name: [ " + productNameElem.getText() + "]");
			if (productNameElem.getText().contains(productName)) {
				productNameElem.click();
				break;
			}
		}
	}
}
