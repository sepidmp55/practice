package com.selenium.greatcourses.tests;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.selenium.libraries.Base;

public class WindowHandlesTest extends Base{

	@Test (enabled = false)
	public void tryWindowHandles(){
		driver.get("https://www.enterprise.com");
		String linkCSS = "body > div:nth-child(4) > div:nth-child(3) > div.custombanner.section > div > div.custom-banner__ctas-wrapper > a";
		driver.findElement(By.cssSelector(linkCSS)).click();
		//Set<String> windowNumber = driver.getWindowHandles();
		//System.out.println("windows1: " + windowNumber);
		
		//Set<String> windowNumber2 = driver.getWindowHandles();
		//System.out.println("windows2: " + windowNumber2);
		driver = myLibrary.switchToWindow(0);
		driver.findElement(By.cssSelector(linkCSS)).click();
		
		driver = myLibrary.switchToWindow(0);
		driver.findElement(By.cssSelector(linkCSS)).click();	
	}
	
	@Test
	public void uploadFileTest(){
		driver.get("https://html.com/input-type-file/");
		myLibrary.customWait(10);
		WebElement uploadElem = driver.findElement(By.id("fileupload"));
		//uploadElem.sendKeys("C:/fileUpload123/test123.txt");
		myLibrary.fileUpload(By.id("fileupload"), "src/test/resources/TestData/test123.txt");		
	}
	
	
	
	
	
	
	
}
